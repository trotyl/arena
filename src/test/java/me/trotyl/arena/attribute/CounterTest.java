package me.trotyl.arena.attribute;

import me.trotyl.arena.Game;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.effect.Flame;
import me.trotyl.arena.effect.Toxin;
import me.trotyl.arena.equipment.Armor;
import me.trotyl.arena.equipment.MediumWeapon;
import me.trotyl.arena.record.CounterDamageRecord;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Fighter;
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

        player1 = Player.create("张三", 10, 5);
        player2 = Fighter.create("李四", 20, 8, MediumWeapon.create("..", 2, 3), Armor.none);
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

        assertThat(damage.genre, is(Genre.counter));
        assertThat(damage.extent, is(0));
        assertThat(damage.distance, is(0));
        assertThat(damage, instanceOf(CounterDamageRecord.class));

        CounterDamageRecord caromDamage = (CounterDamageRecord) damage;

        assertThat(caromDamage.original.extent, is(2));
        assertThat(caromDamage.counter.extent, is(10));

        assertThat(player1.getHealth(), is(0));
        assertThat(player2.getHealth(), is(18));
    }

    @Test
    public void apply_should_have_proper_result_when_defender_died() {

        player1 = Player.create("张三", 1, 5);
        player2 = Fighter.create("李四", 1, 8, MediumWeapon.create("..", 2, 3), Armor.none);
        Game.between(player1, player2);

        DamageRecord damage = Attribute.normalAttack.apply(player1, player2, Attribute.normalAttack, counter);

        assertThat(damage.genre, is(Genre.none));
        assertThat(damage.extent, is(2));
        assertThat(damage.distance, is(0));
    }

    @Test
    public void apply_should_have_proper_result_when_unreachable() {

        Game game = Game.between(player1, player2);
        game.increaseDistance(1);

        DamageRecord damage = Attribute.normalAttack.apply(player1, player2, Attribute.normalAttack, counter);

        assertThat(damage.genre, is(Genre.counter));
        assertThat(damage.extent, is(0));
        assertThat(damage.distance, is(0));
        assertThat(damage, instanceOf(CounterDamageRecord.class));

        CounterDamageRecord caromDamage = (CounterDamageRecord) damage;

        assertThat(caromDamage.original.extent, is(2));
        assertThat(caromDamage.counter, is(DamageRecord.none));

        assertThat(player1.getHealth(), is(10));
        assertThat(player2.getHealth(), is(18));
    }

    @Test
    public void apply_should_have_proper_result_with_flaming_and_toxic() {

        player1 = spy(Player.create("张三", 40, 5));
        player2 = spy(Fighter.create("李四", 20, 8, MediumWeapon.create("..", 2, 3, Toxic.create(2, 2, 1.0f)), Armor.none));
        Game.between(player1, player2);

        DamageRecord damage = Flaming.create(2, 2, 1.0f).apply(player1, player2, Attribute.normalAttack, counter);

        assertThat(damage.genre, is(Genre.counter));
        assertThat(damage.extent, is(0));
        assertThat(damage.distance, is(0));
        assertThat(damage, instanceOf(CounterDamageRecord.class));

        CounterDamageRecord caromDamage = (CounterDamageRecord) damage;

        assertThat(caromDamage.original.extent, is(2));
        assertThat(caromDamage.original.genre, is(Genre.flaming));

        assertThat(caromDamage.counter.extent, is(10));
        assertThat(caromDamage.counter.genre, is(Genre.toxic));

        assertThat(player1.getHealth(), is(30));
        assertThat(player1.getEffect(), instanceOf(Toxin.class));
        assertThat(player2.getHealth(), is(18));
        assertThat(player2.getEffect(), instanceOf(Flame.class));
    }

    @Test
    public void apply_should_have_proper_result_with_striking_and_striking() {

        player1 = spy(Player.create("张三", 40, 5));
        player2 = spy(Fighter.create("李四", 20, 8, MediumWeapon.create("..", 2, 3, Striking.create(1.0f)), Armor.none));
        Game.between(player1, player2);

        DamageRecord damage = Striking.create(1.0f).apply(player1, player2, Attribute.normalAttack, counter);

        assertThat(damage.genre, is(Genre.counter));
        assertThat(damage.extent, is(0));
        assertThat(damage.distance, is(0));
        assertThat(damage, instanceOf(CounterDamageRecord.class));

        CounterDamageRecord caromDamage = (CounterDamageRecord) damage;

        assertThat(caromDamage.original.extent, is(6));
        assertThat(caromDamage.original.genre, is(Genre.striking));

        assertThat(caromDamage.counter.extent, is(30));
        assertThat(caromDamage.counter.genre, is(Genre.striking));

        assertThat(player1.getHealth(), is(10));
        assertThat(player1.getEffect(), is(Effect.none));
        assertThat(player2.getHealth(), is(14));
        assertThat(player2.getEffect(), is(Effect.none));
    }

    @Test
    public void apply_should_have_proper_invocation_with_effect() {

        player1 = spy(player1);
        player2 = spy(player2);

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

        player1 = spy(player1);
        player2 = spy(player2);

        when(random.nextFloat()).thenReturn(2.0f);
        Attribute.normalAttack.apply(player1, player2, Attribute.normalAttack, counter);

        InOrder inOrder = inOrder(player1, player2);
        inOrder.verify(player1).getAggressivity();
        inOrder.verify(player2).getDefence();
        inOrder.verify(player2).suffer(argThat(instanceOf(DamageRecord.class)), eq(Effect.none));
    }
}
