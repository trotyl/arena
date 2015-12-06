package me.trotyl.arena.parser;

import me.trotyl.arena.attribute.*;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class AttributeParserTest {

    AttributeParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new AttributeParser();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void parse_should_have_proper_result_for_toxic() {

        String json = "" +
                "{" +
                "  \"genre\": \"toxic\"," +
                "  \"rate\": 0.5," +
                "  \"extent\": 4," +
                "  \"limit\": 2" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Attribute attribute = parser.parse(object);

        assertThat(attribute, instanceOf(Toxic.class));

        Toxic toxic = (Toxic) attribute;
        assertThat(toxic.getRate(), is(0.5f));
        assertThat(toxic.getExtent(), is(4));
        assertThat(toxic.getLimit(), is(2));
    }

    @Test
    public void parse_should_have_proper_result_for_flaming() {

        String json = "" +
                "{" +
                "  \"genre\": \"flaming\"," +
                "  \"rate\": 0.5," +
                "  \"extent\": 4," +
                "  \"limit\": 2" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Attribute attribute = parser.parse(object);

        assertThat(attribute, instanceOf(Flaming.class));

        Flaming flaming = (Flaming) attribute;
        assertThat(flaming.getRate(), is(0.5f));
        assertThat(flaming.getExtent(), is(4));
        assertThat(flaming.getLimit(), is(2));
    }

    @Test
    public void parse_should_have_proper_result_for_freezing() {

        String json = "" +
                "{" +
                "  \"genre\": \"freezing\"," +
                "  \"rate\": 0.5," +
                "  \"limit\": 2" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Attribute attribute = parser.parse(object);

        assertThat(attribute, instanceOf(Freezing.class));

        Freezing freezing = (Freezing) attribute;
        assertThat(freezing.getRate(), is(0.5f));
        assertThat(freezing.getLimit(), is(2));
    }

    @Test
    public void parse_should_have_proper_result_for_dizzy() {

        String json = "" +
                "{" +
                "  \"genre\": \"dizzy\"," +
                "  \"rate\": 0.5" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Attribute attribute = parser.parse(object);

        assertThat(attribute, instanceOf(Dizzy.class));

        Dizzy dizzy = (Dizzy) attribute;
        assertThat(dizzy.getRate(), is(0.5f));
    }
}
