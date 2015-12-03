package me.trotyl.arena.attribute;

import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.role.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class AttributeTest {

    private Attribute attribute;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() throws Exception {
        Random random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);
        Attribute.config(random);

        attribute = Attribute.none;

        player1 = spy(new Player("张三", 10, 5));
        player2 = spy(new Player("李四", 20, 8));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_be_applicable_to_players() {
        DamageRecord damage = attribute.apply(player1, player2);
        PlayerRecord player1Record = player1.record();
        PlayerRecord player2Record = player2.record();

        assertThat(damage.genre, is(Genre.none));
        assertThat(damage.extent, is(5));

        assertThat(player1Record.health(), is(10));
        assertThat(player2Record.health(), is(15));
    }

    @Test
    public void should_depends_on_proper_interface() {
        attribute.apply(player1, player2);

        InOrder inOrder = inOrder(player1, player2);
        inOrder.verify(player1).aggressivity();
        inOrder.verify(player2).defence();
        inOrder.verify(player2).suffer(5, Effect.none);
        verifyNoMoreInteractions(player1, player2);
    }
}