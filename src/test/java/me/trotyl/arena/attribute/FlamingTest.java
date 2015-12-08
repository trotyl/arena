package me.trotyl.arena.attribute;

import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.effect.Flame;
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


public class FlamingTest {

    private Random random;
    private Flaming flaming;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() throws Exception {

        random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);

        Attribute.config(random);

        flaming = new Flaming(2, 2, 0.5f);

        player1 = spy(Player.create("张三", 10, 5));
        player2 = spy(Player.create("李四", 20, 8));
    }

    @After
    public void tearDown() throws Exception {
        Attribute.config(new Random());
    }

    @Test
    public void create_should_have_proper_result_when_valid() {
        assertThat(Flaming.create(1, 1, 0.5f), instanceOf(Flaming.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_should_have_proper_result_when_extent_less_than_0() {
        Flaming.create(-1, 1, 0.5f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_should_have_proper_result_when_limit_less_than_0() {
        Flaming.create(1, -1, 0.5f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_should_have_proper_result_when_rate_less_than_0() {
        Flaming.create(1, 1, -0.125f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_should_have_proper_result_when_rate_greater_than_1() {
        Flaming.create(1, 1, 1.125f);
    }

    @Test
    public void apply_should_have_proper_result() {

        DamageRecord damage = flaming.apply(player1, player2, Attribute.normalAttack);

        PlayerRecord player1Record = player1.record();
        PlayerRecord player2Record = player2.record();

        assertThat(damage.genre, is(Genre.flaming));
        assertThat(damage.extent, is(5));

        assertThat(player1Record.getHealth(), is(10));
        assertThat(player2Record.getHealth(), is(15));
    }

    @Test
    public void apply_should_have_proper_invocation_with_effect() {

        flaming.apply(player1, player2, Attribute.normalAttack);

        InOrder inOrder = inOrder(player1, player2);
        inOrder.verify(player1).getAggressivity();
        inOrder.verify(player2).getDefence();
        inOrder.verify(player2).suffer(eq(5), argThat(instanceOf(Flame.class)));
    }

    @Test
    public void apply_should_have_proper_invocation_without_effect() {

        when(random.nextFloat()).thenReturn(2.0f);
        flaming.apply(player1, player2, Attribute.normalAttack);

        InOrder inOrder = inOrder(player1, player2);
        inOrder.verify(player1).getAggressivity();
        inOrder.verify(player2).getDefence();
        inOrder.verify(player2).suffer(5, Effect.none);
    }
}
