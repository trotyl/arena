package me.trotyl.arena;

import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Toxic;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ParserTest {

    Parser parser;

    @Before
    public void setUp() throws Exception {
        parser = new Parser();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void parse_armor_should_have_proper_result() {
        String json = "" +
                "{" +
                "  \"defence\": 5" +
                "}";

        JSONObject object = getObject(json);
        Armor armor = parser.parseArmor(object);

        assertThat(armor.defence(), is(5));
    }

    @Test
    public void parse_attribute_should_have_proper_result_for_toxic() {
        String json = "" +
                "{" +
                "  \"genre\": \"toxic\"," +
                "  \"rate\": 0.5," +
                "  \"extent\": 4," +
                "  \"limit\": 2" +
                "}";

        JSONObject object = getObject(json);
        Attribute attribute = parser.parseAttribute(object);

        assertThat(attribute, instanceOf(Toxic.class));

        Toxic toxic = (Toxic) attribute;
        assertThat(toxic.rate, is(0.5f));
        assertThat(toxic.extent, is(4));
        assertThat(toxic.limit, is(2));
    }

    private JSONObject getObject(String json) {
        JSONTokener tokener = new JSONTokener(json);
        return (JSONObject) tokener.nextValue();
    }
}