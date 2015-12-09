package me.trotyl.arena.parser;

import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.role.*;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PlayerParserTest {

    PlayerParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new PlayerParser(new ArmorParser(), new WeaponParser(new AttributeParser()));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void parse_should_have_proper_result_for_normal_player() {

        String json = "" +
                "{" +
                "  \"name\": \"张三\"," +
                "  \"role\": \"normal\"," +
                "  \"health\": 10," +
                "  \"aggressivity\": 5" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Player player = parser.parse(object);

        assertThat(player, not(instanceOf(Soldier.class)));
        assertThat(player.getName(), is("张三"));
        assertThat(player.getHealth(), is(10));
        assertThat(player.getAggressivity(), is(5));
    }

    @Test
    public void parse_should_have_proper_result_for_assassin() {

        String json = "" +
                "{" +
                "  \"name\": \"张三\"," +
                "  \"role\": \"assassin\"," +
                "  \"health\": 10," +
                "  \"aggressivity\": 5" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Player player = parser.parse(object);

        assertThat(player, instanceOf(Assassin.class));

        Assassin assassin = (Assassin) player;

        assertThat(assassin.getName(), is("张三"));
        assertThat(assassin.getHealth(), is(10));
        assertThat(assassin.getAggressivity(), is(5));
    }

    @Test
    public void parse_should_have_proper_result_for_fighter() {

        String json = "" +
                "{" +
                "  \"name\": \"张三\"," +
                "  \"role\": \"fighter\"," +
                "  \"health\": 10," +
                "  \"aggressivity\": 5" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Player player = parser.parse(object);

        assertThat(player, instanceOf(Fighter.class));

        Fighter fighter = (Fighter) player;

        assertThat(fighter.getName(), is("张三"));
        assertThat(fighter.getHealth(), is(10));
        assertThat(fighter.getAggressivity(), is(5));
    }

    @Test
    public void parse_should_have_proper_result_for_knight() {

        String json = "" +
                "{" +
                "  \"name\": \"张三\"," +
                "  \"role\": \"knight\"," +
                "  \"health\": 10," +
                "  \"aggressivity\": 5" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Player player = parser.parse(object);

        assertThat(player, instanceOf(Knight.class));

        Knight knight = (Knight) player;

        assertThat(knight.getName(), is("张三"));
        assertThat(knight.getHealth(), is(10));
        assertThat(knight.getAggressivity(), is(5));
    }

    @Test
    public void parse_should_have_proper_result_for_fighter_with_equipment() {

        Random random = mock(Random.class);
        when(random.nextFloat()).thenReturn(1.0f);
        Attribute.config(random);

        String json = "" +
                "{" +
                "  \"name\": \"张三\"," +
                "  \"role\": \"fighter\"," +
                "  \"health\": 10," +
                "  \"aggressivity\": 5," +
                "  \"weapon\": " +
                "  {" +
                "    \"name\": \"优质木棒\"," +
                "    \"aggressivity\": 5," +
                "    \"defence\": 3," +
                "    \"length\": \"medium\"" +
                "  }," +
                "  \"armor\": " +
                "  {" +
                "    \"defence\": 8" +
                "  }" +
                "}";

        JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
        Player player = parser.parse(object);

        assertThat(player, instanceOf(Fighter.class));

        Fighter soldier = (Fighter) player;

        assertThat(soldier.getName(), is("张三"));
        assertThat(soldier.getHealth(), is(10));
        assertThat(soldier.getAggressivity(), is(10));
        assertThat(soldier.getDefence(), is(8));
        assertThat(soldier.getWeapon().getName(), is("优质木棒"));
        assertThat(soldier.getArmor().getDefence(), is(8));
    }
}
