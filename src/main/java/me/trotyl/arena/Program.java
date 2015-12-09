package me.trotyl.arena;

import me.trotyl.arena.formatter.Formatter;
import me.trotyl.arena.formatter.FormatterGroup;
import me.trotyl.arena.parser.Parser;
import me.trotyl.arena.parser.ParserGroup;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.MoveProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.role.Player;
import org.javatuples.Triplet;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class Program {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Please input the path of player config files, separated by comma, empty to exit: ");
            String pathString = reader.readLine();

            if (pathString.isEmpty()) {
                System.out.println("Goodbye!");
                break;
            }

            String[] pathTuple = pathString.split(",");
            String player1Path = pathTuple[0];
            String player2Path = pathTuple[1];

            FileInputStream player1Stream;
            FileInputStream player2Stream;

            try {
                player1Stream = new FileInputStream(player1Path);
                player2Stream = new FileInputStream(player2Path);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
                System.out.println();
                continue;
            }

            InputStream[] in = {player1Stream, player2Stream};

            Program program = new Program(in, System.out, Parser.defaults(), Formatter.defaults());

            program.run();

            System.out.println();
        }

        reader.close();
    }

    private final PrintStream out;
    private final FormatterGroup formatters;
    private final Game game;

    public Program(InputStream[] in, PrintStream out, ParserGroup parsers, FormatterGroup formatters) {
        this.out = out;
        this.formatters = formatters;

        JSONTokener player1Tokener = new JSONTokener(in[0]);
        JSONObject player1Object = (JSONObject) player1Tokener.nextValue();

        JSONTokener player2Tokener = new JSONTokener(in[1]);
        JSONObject player2Object = (JSONObject) player2Tokener.nextValue();

        Player player1 = parsers.playerParser.parse(player1Object);
        Player player2 = parsers.playerParser.parse(player2Object);

        game = Game.between(player1, player2);
    }

    public void run() {
        while (!game.end()) {
            Triplet<EffectProcedure, MoveProcedure, AttackProcedure> triplet = game.run();

            String effect = formatters.effectFormatter.format(triplet.getValue0());
            if (effect != null) {
                out.println(effect);
            }

            String move = formatters.moveFormatter.format(triplet.getValue1());
            if (move != null) {
                out.println(move);
            }

            String attack = formatters.attackFormatter.format(triplet.getValue2());
            if (attack != null) {
                out.println(attack);
            }
        }

        OverProcedure procedure = game.over();

        String output = formatters.overFormatter.format(procedure);

        out.println(output);
    }
}
