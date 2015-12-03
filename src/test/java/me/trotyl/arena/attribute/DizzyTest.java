package me.trotyl.arena.attribute;

import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class DizzyTest {

    private Dizzy dizzy;

    @Before
    public void setUp() throws Exception {
        Random random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);
        Dizzy.config(random);

        dizzy = new Dizzy(2);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_apply_to_playersPlayer() {
        Player player1 = new Player("张三", 10, 5);
        Player player2 = new Player("李四", 20, 8);

        DamageRecord damage = dizzy.apply(player1, player2);

        assertThat(damage.genre, is(Genre.dizzy));
        assertThat(damage.extent, is(5));
    }
}