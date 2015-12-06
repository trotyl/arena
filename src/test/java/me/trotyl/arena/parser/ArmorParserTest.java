package me.trotyl.arena.parser;

import me.trotyl.arena.armor.Armor;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ArmorParserTest {

    ArmorParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new ArmorParser();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void parse_should_have_proper_result() {

        String json = "" +
                "{" +
                "  \"defence\": 5" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Armor armor = parser.parse(object);

        assertThat(armor.getDefence(), is(5));
    }
}
