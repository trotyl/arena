package me.trotyl.arena.parser;


import me.trotyl.arena.equipment.Armor;
import org.json.JSONObject;

public class ArmorParser extends Parser<JSONObject, Armor> {

    @Override
    public Armor parse(JSONObject object) {

        int defence = object.getInt("defence");

        return Armor.create(defence);
    }
}
