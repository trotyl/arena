package me.trotyl.arena.parser;

import me.trotyl.arena.attribute.Dizzy;
import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class WeaponParserTest {

    WeaponParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new WeaponParser(new AttributeParser());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void parse_should_have_proper_result_without_attribute() {

        String json = "" +
                "{" +
                "  \"name\": \"优质木棒\"," +
                "  \"aggressivity\": 5," +
                "  \"length\": \"medium\"" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Weapon weapon = parser.parse(object);

        assertThat(weapon.getAggressivity(), is(5));
        assertThat(weapon.getName(), is("优质木棒"));
        assertThat(weapon.getLength(), is(Length.medium));
    }

    @Test
    public void parse_should_have_proper_result_with_attribute() {

        String json = "" +
                "{" +
                "  \"name\": \"优质木棒\"," +
                "  \"aggressivity\": 5," +
                "  \"length\": \"medium\"," +
                "  \"attribute\": " +
                "  {" +
                "    \"genre\": \"dizzy\"," +
                "    \"rate\": 0.5" +
                "  }" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Weapon weapon = parser.parse(object);

        assertThat(weapon.getAggressivity(), is(5));
        assertThat(weapon.getName(), is("优质木棒"));
        assertThat(weapon.getLength(), is(Length.medium));
        assertThat(weapon.getAttribute(), instanceOf(Dizzy.class));
    }
}
