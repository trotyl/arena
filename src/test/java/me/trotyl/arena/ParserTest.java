package me.trotyl.arena;

import me.trotyl.arena.armor.Armor;
import me.trotyl.arena.attribute.*;
import me.trotyl.arena.role.*;
import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
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
        assertThat(toxic.rate(), is(0.5f));
        assertThat(toxic.extent(), is(4));
        assertThat(toxic.limit(), is(2));
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
        assertThat(flaming.rate(), is(0.5f));
        assertThat(flaming.extent(), is(4));
        assertThat(flaming.limit(), is(2));
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
        assertThat(freezing.rate(), is(0.5f));
        assertThat(freezing.limit(), is(2));
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
        assertThat(dizzy.rate(), is(0.5f));
    }

    @Test
    public void parse_player_should_have_proper_result_for_normal_player() {

        String json = "" +
                "{" +
                "  \"name\": \"张三\"," +
                "  \"role\": \"normal\"," +
                "  \"health\": 10," +
                "  \"aggressivity\": 5" +
                "}";

        JSONObject object = getObject(json);
        Player player = parser.parsePlayer(object);

        assertThat(player, not(instanceOf(Soldier.class)));
        assertThat(player.name(), is("张三"));
        assertThat(player.health(), is(10));
        assertThat(player.aggressivity(), is(5));
    }

    @Test
    public void parse_player_should_have_proper_result_for_assassin() {

        String json = "" +
                "{" +
                "  \"name\": \"张三\"," +
                "  \"role\": \"assassin\"," +
                "  \"health\": 10," +
                "  \"aggressivity\": 5" +
                "}";

        JSONObject object = getObject(json);
        Player player = parser.parsePlayer(object);

        assertThat(player, instanceOf(Assassin.class));

        Assassin assassin = (Assassin) player;

        assertThat(assassin.name(), is("张三"));
        assertThat(assassin.health(), is(10));
        assertThat(assassin.aggressivity(), is(5));
    }

    @Test
    public void parse_player_should_have_proper_result_for_fighter() {

        String json = "" +
                "{" +
                "  \"name\": \"张三\"," +
                "  \"role\": \"fighter\"," +
                "  \"health\": 10," +
                "  \"aggressivity\": 5" +
                "}";

        JSONObject object = getObject(json);
        Player player = parser.parsePlayer(object);

        assertThat(player, instanceOf(Fighter.class));

        Fighter fighter = (Fighter) player;

        assertThat(fighter.name(), is("张三"));
        assertThat(fighter.health(), is(10));
        assertThat(fighter.aggressivity(), is(5));
    }

    @Test
    public void parse_player_should_have_proper_result_for_knight() {

        String json = "" +
                "{" +
                "  \"name\": \"张三\"," +
                "  \"role\": \"knight\"," +
                "  \"health\": 10," +
                "  \"aggressivity\": 5" +
                "}";

        JSONObject object = getObject(json);
        Player player = parser.parsePlayer(object);

        assertThat(player, instanceOf(Knight.class));

        Knight knight = (Knight) player;

        assertThat(knight.name(), is("张三"));
        assertThat(knight.health(), is(10));
        assertThat(knight.aggressivity(), is(5));
    }

    @Test
    public void parse_player_should_have_proper_result_for_fighter_with_equipment() {

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
                "    \"length\": \"medium\"" +
                "  }," +
                "  \"armor\": " +
                "  {" +
                "    \"defence\": 8" +
                "  }" +
                "}";

        JSONObject object = getObject(json);
        Player player = parser.parsePlayer(object);

        assertThat(player, instanceOf(Soldier.class));

        Soldier soldier = (Soldier) player;

        assertThat(soldier.name(), is("张三"));
        assertThat(soldier.health(), is(10));
        assertThat(soldier.aggressivity(), is(10));
        assertThat(soldier.weapon().name(), is("优质木棒"));
        assertThat(soldier.armor().defence(), is(8));
    }

    @Test
    public void parse_weapon_should_have_proper_result_without_attribute() {

        String json = "" +
                "{" +
                "  \"name\": \"优质木棒\"," +
                "  \"aggressivity\": 5," +
                "  \"length\": \"medium\"" +
                "}";

        JSONObject object = getObject(json);
        Weapon weapon = parser.parseWeapon(object);

        assertThat(weapon.aggressivity(), is(5));
        assertThat(weapon.name(), is("优质木棒"));
        assertThat(weapon.length(), is(Length.medium));
    }

    @Test
    public void parse_weapon_should_have_proper_result_with_attribute() {

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

        JSONObject object = getObject(json);
        Weapon weapon = parser.parseWeapon(object);

        assertThat(weapon.aggressivity(), is(5));
        assertThat(weapon.name(), is("优质木棒"));
        assertThat(weapon.length(), is(Length.medium));
        assertThat(weapon.attribute(), instanceOf(Dizzy.class));
    }

    private JSONObject getObject(String json) {

        JSONTokener tokener = new JSONTokener(json);
        return (JSONObject) tokener.nextValue();
    }
}