package me.trotyl.arena.parser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArrayParser<TResult> extends Parser<JSONArray, List<TResult>> {

    private final Parser<JSONObject, TResult> parser;

    public ArrayParser(Parser<JSONObject, TResult> parser) {

        this.parser = parser;
    }

    @Override
    public List<TResult> parse(JSONArray array) {

        ArrayList<TResult> results = new ArrayList<>();

        for (Object object : array) {
            TResult result = parser.parse((JSONObject) object);
            results.add(result);
        }

        return results;
    }
}
