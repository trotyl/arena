package me.trotyl.arena.attribute;

import me.trotyl.arena.Game;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.CounterDamageRecord;
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


public class CounterTest {

    private Random random;
    private Counter counter;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() throws Exception {

        random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);

        Attribute.config(random);

        counter = new Counter(3);

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
        assertThat(Carom.create(), instanceOf(Carom.class));
    }

    @Test
    public void apply_should_have_proper_result() {

        DamageRecord damage = Attribute.normalAttack.apply(player1, player2, Attribute.normalAttack, counter);

        PlayerRecord player1Record = player1.record();
        PlayerRecord player2Record = player2.record();

        assertThat(damage.genre, is(Genre.counter));
        assertThat(damage.extent, is(0));
        assertThat(damage.distance, is(0));
        assertThat(damage, instanceOf(CounterDamageRecord.class));

        CounterDamageRecord caromDamage = (CounterDamageRecord) damage;

        assertThat(caromDamage.original.extent, is(2));
        assertThat(caromDamage.counter.extent, is(8));

        assertThat(player1Record.getHealth(), is(2));
        assertThat(player2Record.getHealth(), is(18));
    }

    @Test
    public void apply_should_have_proper_result_when_defender_died() {

        player1 = spy(Player.create("张三", 1, 5));
        player2 = spy(Player.create("李四", 1, 8));
        Game.between(player1, player2);

        DamageRecord damage = Attribute.normalAttack.apply(player1, player2, Attribute.normalAttack, counter);

        assertThat(damage.genre, is(Genre.none));
        assertThat(damage.extent, is(2));
        assertThat(damage.distance, is(0));
    }

    @Test
    public void apply_should_have_proper_invocation_with_effect() {

        Attribute.normalAttack.apply(player1, player2, Attribute.normalAttack, counter);

        InOrder inOrder = inOrder(player1, player2);
        inOrder.verify(player1).getAggressivity();
        inOrder.verify(player2).getDefence();
        inOrder.verify(player2).suffer(argThat(instanceOf(DamageRecord.class)), eq(Effect.none));
        inOrder.verify(player2).getAggressivity();
        inOrder.verify(player1).getDefence();
        inOrder.verify(player1).suffer(argThat(instanceOf(DamageRecord.class)), eq(Effect.none));
    }

    @Test
    public void apply_should_have_proper_invocation_without_effect() {

        when(random.nextFloat()).thenReturn(2.0f);
        Attribute.normalAttack.apply(player1, player2, Attribute.normalAttack, counter);

        InOrder inOrder = inOrder(player1, player2);
        inOrder.verify(player1).getAggressivity();
        inOrder.verify(player2).getDefence();
        inOrder.verify(player2).suffer(argThat(instanceOf(DamageRecord.class)), eq(Effect.none));
    }
}
