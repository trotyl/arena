package me.trotyl.arena.parser;

import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.equipment.*;
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

        Attribute attribute;

        if (object.has("attributes")) {

            JSONArray attributeArray = object.getJSONArray("attributes");
            List<Attribute> attributes = attributesParser.parse(attributeArray);

            attribute = Attribute.create(attributes);
        } else {

            attribute = Attribute.none;
        }

        switch (lengthStr) {

            case "short":

                return ShortWeapon.create(name, aggressivity, attribute);

            case "medium":

                int defence = object.getInt("defence");

                return MediumWeapon.create(name, aggressivity, defence, attribute);

            case "long":

                int repel = object.getInt("repel");

                return LongWeapon.create(name, aggressivity, repel, attribute);

            default:

                return Weapon.none;
        }
    }
}
