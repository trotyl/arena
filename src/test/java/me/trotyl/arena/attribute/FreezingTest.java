package me.trotyl.arena.attribute;

import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.effect.Freeze;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.role.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Random;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class FreezingTest {

    private Random random;
    private Freezing freezing;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() throws Exception {

        random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);

        Attribute.config(random);

        freezing = new Freezing(2, 0.5f);

        player1 = spy(Player.create("张三", 10, 5));
        player2 = spy(Player.create("李四", 20, 8));
    }

    @After
    public void tearDown() throws Exception {
        Attribute.config(new Random());
    }

    @Test
    public void create_should_have_proper_result_when_valid() {
        assertThat(Freezing.create(1, 0.5f), instanceOf(Freezing.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_should_have_proper_result_when_limit_less_than_0() {
        Freezing.create(-1, 0.5f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_should_have_proper_result_when_rate_less_than_0() {
        Freezing.create(1, -0.125f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_should_have_proper_result_when_rate_greater_than_1() {
        Freezing.create(1, 1.125f);
    }

    @Test
    public void apply_should_have_proper_result() {

        DamageRecord damage = freezing.apply(player1, player2, Attribute.normalAttack);

        PlayerRecord player1Record = player1.record();
        PlayerRecord player2Record = player2.record();

        assertThat(damage.genre, is(Genre.freezing));
        assertThat(damage.extent, is(5));

        assertThat(player1Record.getHealth(), is(10));
        assertThat(player2Record.getHealth(), is(15));
    }

    @Test
    public void apply_should_have_proper_invocation_with_effect() {

        freezing.apply(player1, player2, Attribute.normalAttack);

        InOrder inOrder = inOrder(player1, player2);
        inOrder.verify(player1).getAggressivity();
        inOrder.verify(player2).getDefence();
        inOrder.verify(player2).suffer(eq(5), argThat(instanceOf(Freeze.class)));

        verifyNoMoreInteractions(player1, player2);
    }

    @Test
    public void apply_should_have_proper_invocation_without_effect() {

        when(random.nextFloat()).thenReturn(2.0f);
        freezing.apply(player1, player2, Attribute.normalAttack);

        InOrder inOrder = inOrder(player1, player2);
        inOrder.verify(player1).getAggressivity();
        inOrder.verify(player2).getDefence();
        inOrder.verify(player2).suffer(5, Effect.none);

        verifyNoMoreInteractions(player1, player2);
    }
}
