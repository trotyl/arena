package me.trotyl.arena;

import me.trotyl.arena.attribute.*;
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

    @Test
    public void parse_attribute_should_have_proper_result_for_flaming() {
        String json = "" +
                "{" +
                "  \"genre\": \"flaming\"," +
                "  \"rate\": 0.5," +
                "  \"extent\": 4," +
                "  \"limit\": 2" +
                "}";

        JSONObject object = getObject(json);
        Attribute attribute = parser.parseAttribute(object);

        assertThat(attribute, instanceOf(Flaming.class));

        Flaming flaming = (Flaming) attribute;
        assertThat(flaming.rate, is(0.5f));
        assertThat(flaming.extent, is(4));
        assertThat(flaming.limit, is(2));
    }

    @Test
    public void parse_attribute_should_have_proper_result_for_freezing() {
        String json = "" +
                "{" +
                "  \"genre\": \"freezing\"," +
                "  \"rate\": 0.5," +
                "  \"limit\": 2" +
                "}";

        JSONObject object = getObject(json);
        Attribute attribute = parser.parseAttribute(object);

        assertThat(attribute, instanceOf(Freezing.class));

        Freezing freezing = (Freezing) attribute;
        assertThat(freezing.rate, is(0.5f));
        assertThat(freezing.limit, is(2));
    }

    @Test
    public void parse_attribute_should_have_proper_result_for_dizzy() {
        String json = "" +
                "{" +
                "  \"genre\": \"dizzy\"," +
                "  \"rate\": 0.5" +
                "}";

        JSONObject object = getObject(json);
        Attribute attribute = parser.parseAttribute(object);

        assertThat(attribute, instanceOf(Dizzy.class));

        Dizzy dizzy = (Dizzy) attribute;
        assertThat(dizzy.rate, is(0.5f));
    }

    @Test
    public void parse_weapon_should_have_proper_result_without_attribute() {
        String json = "" +
                "{" +
                "  \"name\": \"优质木棒\"," +
                "  \"aggressivity\": 5" +
                "}";

        JSONObject object = getObject(json);
        Weapon weapon = parser.parseWeapon(object);

        assertThat(weapon.aggressivity(), is(5));
        assertThat(weapon.name(), is("优质木棒"));
    }

    @Test
    public void parse_weapon_should_have_proper_result_with_attribute() {
        String json = "" +
                "{" +
                "  \"name\": \"优质木棒\"," +
                "  \"aggressivity\": 5," +
                "  \"attribute\": " +
                "  {" +
                "    \"genre\": \"dizzy\"," +
                "    \"rate\": 0.5" +
                "  }" +
                "}";

        JSONObject object = getObject(json);
        Weapon weapon = parser.parseWeapon(object);

        assertThat(weapon.aggressivity(), is(5));
        assertThat(weapon.name(), is("优质木棒"));
        assertThat(weapon.attribute, instanceOf(Dizzy.class));
    }

    private JSONObject getObject(String json) {
        JSONTokener tokener = new JSONTokener(json);
        return (JSONObject) tokener.nextValue();
    }
}