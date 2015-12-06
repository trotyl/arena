package me.trotyl.arena.parser;

import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;


public class WeaponParser extends Parser<JSONObject, Weapon> {

    private ArrayParser<Attribute> attributesParser;

    public WeaponParser(AttributeParser attributeParser) {

        this.attributesParser = new ArrayParser<Attribute>(attributeParser);
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

        if (!object.has("attributes")) {
            return Weapon.create(name, aggressivity, length);
        }

        JSONArray attributeArray = object.getJSONArray("attributes");
        List<Attribute> attributes = attributesParser.parse(attributeArray);

        return Weapon.create(name, aggressivity, length, Attribute.create(attributes));
    }
}
