package me.trotyl.arena.parser;

import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;
import org.json.JSONObject;


public class WeaponParser extends Parser<JSONObject, Weapon> {

    private AttributeParser attributeParser;

    public WeaponParser(AttributeParser attributeParser) {

        this.attributeParser = attributeParser;
    }

    @Override
    public Weapon parse(JSONObject object) {

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
        Attribute attribute = attributeParser.parse(attrObject);

        return Weapon.create(name, aggressivity, length, attribute);
    }
}
