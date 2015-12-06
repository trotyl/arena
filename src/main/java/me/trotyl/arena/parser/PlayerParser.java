package me.trotyl.arena.parser;


import me.trotyl.arena.equipment.Armor;
import me.trotyl.arena.role.*;
import me.trotyl.arena.equipment.Weapon;
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

        if (role.equals("normal")) {
            return Player.create(name, health, aggressivity);
        }

        Soldier soldier = role.equals("assassin")? Assassin.create(name, health, aggressivity):
                role.equals("fighter")? Fighter.create(name, health, aggressivity):
                        role.equals("knight")? Knight.create(name, health, aggressivity):
                                Soldier.create(name, health, aggressivity);

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
}
