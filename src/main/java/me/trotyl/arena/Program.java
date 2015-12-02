package me.trotyl.arena;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class Program {

    private final PrintStream out;
    private final InputStream in;

    public Program(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please input the path of config file: ");
        String path = reader.readLine();
        FileInputStream in = new FileInputStream(path);

        Program program = new Program(in, System.out);
        program.run();

        reader.close();
    }

    public void run() {
        JSONTokener tokener = new JSONTokener(in);
        JSONObject configObject = (JSONObject) tokener.nextValue();

        JSONObject player1Object = configObject.getJSONObject("player1");
        Player player1 = parsePlayer(player1Object);

        JSONObject player2Object = configObject.getJSONObject("player2");
        Player player2 = parsePlayer(player2Object);

        Game game = Game.between(player1, player2);
        Player loser = game.run();

        out.println(loser.getName() + "被打败了.");
    }

    private Player parsePlayer(JSONObject object) {
        String name = object.getString("name");
        int health = object.getInt("health");
        int aggressivity = object.getInt("aggressivity");

        return new Player(name, health, aggressivity);
    }
}
