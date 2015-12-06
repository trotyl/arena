package me.trotyl.arena.parser;


import me.trotyl.arena.armor.Armor;
import me.trotyl.arena.attribute.*;
import me.trotyl.arena.role.*;
import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;
import org.json.JSONObject;

public class Parser {

    public Armor parseArmor(JSONObject object) {

        int defence = object.getInt("defence");

        return Armor.create(defence);
    }

    public Attribute parseAttribute(JSONObject object) {

        String genre = object.getString("genre");
        float rate = (float) object.getDouble("rate");

        if (genre.equals("dizzy")) {
            return Dizzy.create(rate);
        } if (genre.equals("striking")) {
            return Striking.create(rate);
        }

        int limit = object.getInt("limit");

        if (genre.equals("freezing")) {
            return Freezing.create(limit, rate);
        }

        int extent = object.getInt("extent");

        if (genre.equals("flaming")) {
            return Flaming.create(extent, limit, rate);
        } if (genre.equals("toxic")) {
            return Toxic.create(extent, limit, rate);
        }

        return Attribute.none;
    }

    public Player parsePlayer(JSONObject object) {

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
            Weapon weapon = parseWeapon(weaponObject);
            soldier.equip(weapon);
        } if (object.has("armor")) {
            JSONObject armorObject = object.getJSONObject("armor");
            Armor armor = parseArmor(armorObject);
            soldier.equip(armor);
        }

        return soldier;
    }

    public Weapon parseWeapon(JSONObject object) {

        String name = object.getString("name");
        int aggressivity = object.getInt("aggressivity");
        String lengthStr = object.getString("length");

        Length length = lengthStr.equals("short")? Length.shorter:
                lengthStr.equals("medium")? Length.medium:
                lengthStr.equals("long")? Length.longer:
                Length.none;

        if (!object.has("attribute")) {
            return Weapon.create(name, aggressivity, length);
        }

        JSONObject attrObject = object.getJSONObject("attribute");
        Attribute attribute = parseAttribute(attrObject);

        return Weapon.create(name, aggressivity, length, attribute);
    }
}
