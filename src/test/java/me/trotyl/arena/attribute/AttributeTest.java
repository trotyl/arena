package me.trotyl.arena.attribute;

import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.role.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AttributeTest {

    private Attribute attribute;

    @Before
    public void setUp() throws Exception {
        Random random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);
        Attribute.config(random);

        attribute = Attribute.none;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_be_applicable_to_players() {
        Player player1 = new Player("张三", 10, 5);
        Player player2 = new Player("李四", 20, 8);

        DamageRecord damage = attribute.apply(player1, player2);
        PlayerRecord player1Record = player1.record();
        PlayerRecord player2Record = player2.record();

        assertThat(damage.genre, is(Genre.none));
        assertThat(damage.extent, is(5));

        assertThat(player1Record.health(), is(10));
        assertThat(player2Record.health(), is(15));
    }
}