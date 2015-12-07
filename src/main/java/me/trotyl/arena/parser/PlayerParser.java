package me.trotyl.arena.parser;


import me.trotyl.arena.equipment.Armor;
import me.trotyl.arena.equipment.Weapon;
import me.trotyl.arena.role.*;
import org.json.JSONObject;

public class PlayerParser extends Parser<JSONObject, Player> {

    private final ArmorParser armorParser;
    private final WeaponParser weaponParser;

    public PlayerParser(ArmorParser armorParser, WeaponParser weaponParser) {

        this.armorParser = armorParser;
        this.weaponParser = weaponParser;
    }

    @Override
    public Player parse(JSONObject object) {

        String name = object.getString("name");
        int health = object.getInt("health");
        int aggressivity = object.getInt("aggressivity");
        String role = object.getString("role");

        Player player = getPlayerByRole(role, name, health, aggressivity);

        if (!(player instanceof Soldier)) {
            return player;
        }

        Soldier soldier = (Soldier) player;

        if (object.has("weapon")) {
            JSONObject weaponObject = object.getJSONObject("weapon");
            Weapon weapon = weaponParser.parse(weaponObject);
            soldier.equip(weapon);
        } if (object.has("armor")) {
            JSONObject armorObject = object.getJSONObject("armor");
            Armor armor = armorParser.parse(armorObject);
            soldier.equip(armor);
        }

        return soldier;
    }

    protected Player getPlayerByRole(String role, String name, int health, int aggressivity) {

        switch (role) {
            case "normal":
                return Player.create(name, health, aggressivity);
            case "assassin":
                return Assassin.create(name, health, aggressivity);
            case "fighter":
                return Fighter.create(name, health, aggressivity);
            case "knight":
                return Knight.create(name, health, aggressivity);
            default:
                throw new IllegalArgumentException("The role is invalid.");
        }
    }
}
