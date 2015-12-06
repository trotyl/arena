package me.trotyl.arena.parser;

import me.trotyl.arena.attribute.CompositeAttribute;
import me.trotyl.arena.attribute.Dizzy;
import me.trotyl.arena.attribute.Freezing;
import me.trotyl.arena.equipment.Length;
import me.trotyl.arena.equipment.Weapon;
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
                "  \"defence\": 0," +
                "  \"length\": \"medium\"" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Weapon weapon = parser.parse(object);

        assertThat(weapon.getAggressivity(), is(5));
        assertThat(weapon.getName(), is("优质木棒"));
        assertThat(weapon.getLength(), is(Length.medium));
    }

    @Test
    public void parse_should_have_proper_result_with_single_attribute() {

        String json = "" +
                "{" +
                "  \"name\": \"优质木棒\"," +
                "  \"aggressivity\": 5," +
                "  \"defence\": 0," +
                "  \"length\": \"medium\"," +
                "  \"attributes\": " +
                "  [{" +
                "    \"genre\": \"dizzy\"," +
                "    \"rate\": 0.5" +
                "  }]" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Weapon weapon = parser.parse(object);

        assertThat(weapon.getAggressivity(), is(5));
        assertThat(weapon.getName(), is("优质木棒"));
        assertThat(weapon.getLength(), is(Length.medium));
        assertThat(weapon.getAttribute(), instanceOf(Dizzy.class));
    }

    @Test
    public void parse_should_have_proper_result_with_multiple_attribute() {

        String json = "" +
                "{" +
                "  \"name\": \"优质木棒\"," +
                "  \"aggressivity\": 5," +
                "  \"defence\": 0," +
                "  \"length\": \"medium\"," +
                "  \"attributes\": " +
                "  [{" +
                "    \"genre\": \"dizzy\"," +
                "    \"rate\": 0.5" +
                "  }, {" +
                "    \"genre\": \"freezing\"," +
                "    \"rate\": 0.25," +
                "    \"limit\": 3" +
                "  }]" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Weapon weapon = parser.parse(object);

        assertThat(weapon.getAggressivity(), is(5));
        assertThat(weapon.getName(), is("优质木棒"));
        assertThat(weapon.getLength(), is(Length.medium));
        assertThat(weapon.getAttribute(), instanceOf(CompositeAttribute.class));

        CompositeAttribute composite = (CompositeAttribute) weapon.getAttribute();

        assertThat(composite.getFirst(), instanceOf(Dizzy.class));
        assertThat(composite.getFirst().getRate(), is(0.5f));
        assertThat(composite.getSecond(), instanceOf(Freezing.class));
        assertThat(composite.getSecond().getRate(), is(0.25f));
        assertThat(composite.getSecond().getLimit(), is(3));
    }
}
