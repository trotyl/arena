package me.trotyl.arena.role;

import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.PlayerRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class PlayerTest {

    Player player1, player2;

    @Before
    public void setUp() throws Exception {
        player1 = new Player("张三", 10, 5);
        player2 = new Player("李四", 20, 8);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void alive_should_have_proper_result() {
        assertThat(player1.alive(), is(true));

        Player player3 = new Player("王二", 1, 1);
        assertThat(player3.alive(), is(true));

        Player player4 = new Player("麻子", 0, 1);
        assertThat(player4.alive(), is(false));
    }

    @Test
    public void should_be_alive_when_health_is_greater_than_zero() throws Exception {
        Player player = new Player("张三", 1, 5);

        boolean isAlive = player.alive();

        assertThat(isAlive, is(true));
    }

    @Test
    public void should_reduce_health_another_when_attack() throws Exception {
        Player player1 = new Player("张三", 10, 5);
        Player player2 = new Player("李四", 10, 3);

        player1.attack(player2);

        assertTrue(player1.alive());
        assertTrue(player2.alive());

        player2.attack(player1);

        assertTrue(player1.alive());
        assertTrue(player2.alive());

        player1.attack(player2);

        assertTrue(player1.alive());
        assertFalse(player2.alive());
    }

    @Test
    public void should_be_able_to_get_right_status() throws Exception {
        Player player1 = new Player("张三", 10, 5);

        PlayerRecord status = player1.record();

        assertThat(status.name(), is("张三"));
        assertThat(status.health(), is(10));
    }

    @Test
    public void should_reduce_health_when_suffered() throws Exception {
        Player player1 = new Player("张三", 10, 5);
        PlayerRecord originalStatus = player1.record();
        player1.suffer(5, Effect.none);
        PlayerRecord finalStatus = player1.record();

        assertThat(originalStatus.health(), is(10));
        assertThat(finalStatus.health(), is(5));
    }
}