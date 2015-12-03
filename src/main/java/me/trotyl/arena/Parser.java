package me.trotyl.arena;


import me.trotyl.arena.attribute.*;
import me.trotyl.arena.role.Player;
import me.trotyl.arena.role.Soldier;
import org.json.JSONObject;

public class Parser {

    public Player parsePlayer(JSONObject object) {
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

    public Weapon parseWeapon(JSONObject object) {
        String name = object.getString("name");
        int aggressivity = object.getInt("aggressivity");

        return new Weapon(name, aggressivity);
    }

    public Armor parseArmor(JSONObject object) {
        int defence = object.getInt("defence");

        return new Armor(defence);
    }

    public Attribute parseAttribute(JSONObject object) {
        String genre = object.getString("genre");
        float rate = (float) object.getDouble("rate");
        if (genre.equals("dizzy")) {
            return new Dizzy(rate);
        }
        if (genre.equals("striking")) {
            return new Striking(rate);
        }

        int limit = object.getInt("limit");
        if (genre.equals("freezing")) {
            return new Freezing(limit, rate);
        }

        int extent = object.getInt("extent");
        if (genre.equals("flaming")) {
            return new Flaming(extent, limit, rate);
        }
        if (genre.equals("toxic")) {
            return new Toxic(extent, limit, rate);
        }

        return Attribute.none;
    }
}
