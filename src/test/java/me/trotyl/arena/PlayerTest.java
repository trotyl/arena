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
        Player player = new Player("张三", 10, 5);

        boolean isAlive = player.isAlive();

        assertThat(isAlive, is(true));
    }
}