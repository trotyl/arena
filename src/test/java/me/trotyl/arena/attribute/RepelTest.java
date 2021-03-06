package me.trotyl.arena.attribute;

import me.trotyl.arena.Game;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.record.RepelDamageRecord;
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


public class RepelTest {

    private Random random;
    private Repel repel;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() throws Exception {

        random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);

        Attribute.config(random);

        repel = new Repel(2);

        player1 = spy(Player.create("张三", 10, 5));
        player2 = spy(Player.create("李四", 20, 8));
        Game.between(player1, player2);
    }

    @After
    public void tearDown() throws Exception {
        Attribute.config(new Random());
    }

    @Test
    public void create_should_have_proper_result_when_valid() {
        assertThat(Repel.create(2), instanceOf(Repel.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_should_have_proper_result_when_less_than_0() {
        Repel.create(-1);
    }

    @Test
    public void apply_should_have_proper_result() {

        DamageRecord damage = repel.apply(player1, player2, Attribute.normalAttack, Attribute.normalDefence);

        PlayerRecord player1Record = player1.record();
        PlayerRecord player2Record = player2.record();

        assertThat(damage.genre, is(Genre.repel));
        assertThat(damage.extent, is(5));
        assertThat(damage.distance, is(2));
        assertThat(damage, instanceOf(RepelDamageRecord.class));

        RepelDamageRecord repelDamage = (RepelDamageRecord) damage;

        assertThat(repelDamage.inner.genre, is(Genre.none));
        assertThat(repelDamage.inner.extent, is(5));

        assertThat(player1Record.getHealth(), is(10));
        assertThat(player2Record.getHealth(), is(15));
    }

    @Test
    public void apply_should_have_proper_invocation_with_effect() {

        repel.apply(player1, player2, Attribute.normalAttack, Attribute.normalDefence);

        InOrder inOrder = inOrder(player1, player2);
        inOrder.verify(player1).getAggressivity();
        inOrder.verify(player2).getDefence();
        inOrder.verify(player2).suffer(argThat(instanceOf(DamageRecord.class)), eq(Effect.none));
    }

    @Test
    public void apply_should_have_proper_invocation_without_effect() {

        when(random.nextFloat()).thenReturn(2.0f);
        repel.apply(player1, player2, Attribute.normalAttack, Attribute.normalDefence);

        InOrder inOrder = inOrder(player1, player2);
        inOrder.verify(player1).getAggressivity();
        inOrder.verify(player2).getDefence();
        inOrder.verify(player2).suffer(argThat(instanceOf(DamageRecord.class)), eq(Effect.none));
    }
}
