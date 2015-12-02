package me.trotyl.arena;

import me.trotyl.arena.role.Player;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.role.Soldier;
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

        while (!game.over()) {
            AttackProcedure procedure = game.runStep();
            String weaponOutput = procedure.attacker.weapon != null?
                    String.format("用%s", procedure.attacker.weapon.name): "";
            String output = String.format("%s%s%s攻击了%s%s, %s受到了%d点伤害, %s剩余生命: %d",
                    procedure.attacker.role, procedure.attacker.name, weaponOutput,
                    procedure.defender.role, procedure.defender.name,
                    procedure.defender.name, procedure.damage,
                    procedure.defender.name, procedure.defender.health);
            out.println(output);
        }

        try {
            OverProcedure procedure = game.overProcedure();
            String output = String.format("%s被打败了.", procedure.loser.name);
            out.println(output);
        } catch (Exception ignored) {}
    }

    private Player parsePlayer(JSONObject object) {
        String name = object.getString("name");
        int health = object.getInt("health");
        int aggressivity = object.getInt("aggressivity");
        String role = object.getString("role");
        if (role.equals("Normal")) {
            return new Player(name, health, aggressivity);
        }

        Soldier soldier = new Soldier(name, health, aggressivity);
        if (object.has("weapon")) {
            JSONObject weaponObject = object.getJSONObject("weapon");
            Weapon weapon = parseWeapon(weaponObject);
            soldier.equip(weapon);
        }
        if (object.has("armor")) {
            JSONObject armorObject = object.getJSONObject("armor");
            Armor armor = parseArmor(armorObject);
            soldier.equip(armor);
        }

        return soldier;
    }

    private Weapon parseWeapon(JSONObject object) {
        String name = object.getString("name");
        int aggressivity = object.getInt("aggressivity");

        return new Weapon(name, aggressivity);
    }

    private Armor parseArmor(JSONObject object) {
        int defence = object.getInt("defence");

        return new Armor(defence);
    }
}
