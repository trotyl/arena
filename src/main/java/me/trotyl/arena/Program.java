package me.trotyl.arena;

import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.role.Player;
import org.javatuples.Pair;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class Program {

    private final PrintStream out;
    private final Formatter formatter;
    private final Game game;

    public Program(InputStream in, PrintStream out, Parser parser, Formatter formatter) {
        this.out = out;
        this.formatter = formatter;

        JSONTokener tokener = new JSONTokener(in);
        JSONObject configObject = (JSONObject) tokener.nextValue();

        JSONObject player1Object = configObject.getJSONObject("player1");
        Player player1 = parser.parsePlayer(player1Object);

        JSONObject player2Object = configObject.getJSONObject("player2");
        Player player2 = parser.parsePlayer(player2Object);

        game = Game.between(player1, player2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Please input the path of config file, empty to exit: ");
            String path = reader.readLine();

            if (path.isEmpty()) {
                System.out.println("Goodbye!");
                break;
            }

            FileInputStream in = new FileInputStream(path);

            Program program = new Program(in, System.out, new Parser(), new Formatter());
            program.run();

            System.out.println();
        }

        reader.close();
    }

    public void run() {
        while (!game.over()) {
            Pair<EffectProcedure, AttackProcedure> pair = game.run();

            String effect = formatter.formatEffect(pair.getValue0());
            if (effect != null) {
                out.println(effect);
            }

            String attack = formatter.formatAttack(pair.getValue1());
            if (attack != null) {
                out.println(attack);
            }
        }

        OverProcedure procedure = game.overProcedure();

        String output = formatter.formatOver(procedure);

        out.println(output);
    }
}
