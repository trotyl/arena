package me.trotyl.arena.parser;


import me.trotyl.arena.attribute.*;
import org.json.JSONObject;

public class AttributeParser extends Parser<JSONObject, AggressiveAttribute> {

    @Override
    public AggressiveAttribute parse(JSONObject object) {

        String genre = object.getString("genre");
        float rate = (float) object.getDouble("rate");

        if (genre.equals("dizzy")) {
            return Dizzy.create(rate);
        } else if (genre.equals("striking")) {
            return Striking.create(rate);
        }

        int limit = object.getInt("limit");

        if (genre.equals("freezing")) {
            return Freezing.create(limit, rate);
        }

        int extent = object.getInt("extent");

        if (genre.equals("flaming")) {
            return Flaming.create(extent, limit, rate);
        } else if (genre.equals("toxic")) {
            return Toxic.create(extent, limit, rate);
        }

        throw new IllegalArgumentException("The attribute is not of any valid type.");
    }
}
