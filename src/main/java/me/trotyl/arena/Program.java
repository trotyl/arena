package me.trotyl.arena;

import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.role.Player;
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

        System.out.println("Please input the path of config file: ");
        String path = reader.readLine();
        FileInputStream in = new FileInputStream(path);

        Program program = new Program(in, System.out, new Parser(), new Formatter());
        program.run();

        reader.close();
    }

    public void run() {
        while (!game.over()) {
            AttackProcedure procedure = game.runStep();

            String output = formatter.formatAttack(procedure);

            out.println(output);
        }

        OverProcedure procedure = game.overProcedure();

        String output = formatter.formatOver(procedure);

        out.println(output);
    }
}
