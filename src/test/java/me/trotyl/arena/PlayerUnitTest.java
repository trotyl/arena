package me.trotyl.arena;

import me.trotyl.arena.status.PlayerStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class PlayerUnitTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_be_alive_when_health_is_greater_than_zero() throws Exception {
        Player player = new Player("张三", 1, 5);

        boolean isAlive = player.alive();

        assertThat(isAlive, is(true));
    }

    @Test
    public void should_not_be_alive_when_health_is_not_greater_than_zero() throws Exception {
        Player player1 = new Player("张三", 0, 5);
        Player player2 = new Player("李四", -1, 5);

        boolean isPlayer1Alive = player1.alive();
        boolean isPlayer2Alive = player2.alive();

        assertThat(isPlayer1Alive, is(false));
        assertThat(isPlayer2Alive, is(false));
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

        PlayerStatus status = player1.status();

        assertThat(status.name(), is("张三"));
        assertThat(status.health(), is(10));
    }

    @Test
    public void should_reduce_health_when_suffered() throws Exception {
        Player player1 = new Player("张三", 10, 5);
        PlayerStatus originalStatus = player1.status();
        player1.suffer(5);
        PlayerStatus finalStatus = player1.status();

        assertThat(originalStatus.health(), is(10));
        assertThat(finalStatus.health(), is(5));
    }
}