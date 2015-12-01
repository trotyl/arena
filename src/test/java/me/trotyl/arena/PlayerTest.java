package me.trotyl.arena;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class PlayerTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_be_alive_when_health_is_greater_than_zero() throws Exception {
        Player player = new Player("张三", 1, 5);

        boolean isAlive = player.isAlive();

        assertThat(isAlive, is(true));
    }

    @Test
    public void should_not_be_alive_when_health_is_not_greater_than_zero() throws Exception {
        Player player1 = new Player("张三", 0, 5);
        Player player2 = new Player("张三", -1, 5);

        boolean isPlayer1Alive = player1.isAlive();
        boolean isPlayer2Alive = player2.isAlive();

        assertThat(isPlayer1Alive, is(false));
        assertThat(isPlayer2Alive, is(false));
    }
}