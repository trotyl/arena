package me.trotyl.arena;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.io.PrintStream;

public class Program {

    private final PrintStream out;
    private final InputStream in;

    public Program(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public static void main(String[] args) {

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
