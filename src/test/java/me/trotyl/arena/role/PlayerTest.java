package me.trotyl.arena.role;

import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.effect.Flame;
import me.trotyl.arena.effect.Toxin;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.ArmorRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.record.WeaponRecord;
import org.javatuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;


public class PlayerTest {

    Player player1, player2;

    @Before
    public void setUp() throws Exception {

        player1 = Player.create("张三", 10, 5);
        player2 = Player.create("李四", 20, 8);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void aggressivity_should_have_proper_result() {

        assertThat(player1.aggressivity(), is(5));
        assertThat(player2.aggressivity(), is(8));
    }

    @Test
    public void alive_should_have_proper_result() {

        assertThat(player1.alive(), is(true));

        Player player3 = Player.create("王二", 1, 1);
        assertThat(player3.alive(), is(true));

        Player player4 = Player.create("麻子", 0, 1);
        assertThat(player4.alive(), is(false));
    }

    @Test
    public void attack_should_have_proper_result() {

        Player player3 = spy(player1);
        Player player4 = spy(player2);

        Pair<EffectProcedure, AttackProcedure> pair = player3.attack(player4);
        EffectProcedure effectProcedure = pair.getValue0();
        AttackProcedure attackProcedure = pair.getValue1();

        assertThat(effectProcedure, is(EffectProcedure.none));
        assertThat(attackProcedure.attacker.name(), is("张三"));
        assertThat(attackProcedure.attackable.name(), is("李四"));
        assertThat(attackProcedure.attackable.health(), is(15));
        assertThat(attackProcedure.damage.genre, is(Genre.none));
        assertThat(attackProcedure.damage.extent, is(5));

        InOrder inOrder = inOrder(player3, player4);
        inOrder.verify(player3).record();
        inOrder.verify(player3).aggressivity();
        inOrder.verify(player4).defence();
        inOrder.verify(player4).suffer(5, Effect.none);
        inOrder.verify(player3).record();
        inOrder.verify(player4).record();

        Player player5 = Player.create("王二", 1, 2);
        Player player6 = spy(Player.create("麻子", 1, 2));
        player5.suffer(0, Flame.create(5, 2));

        assertThat(player5.health(), is(1));
        assertThat(player6.health(), is(1));

        assertThat(player5.effect, instanceOf(Flame.class));

        Pair<EffectProcedure, AttackProcedure> attack = player5.attack(player6);

        assertThat(player5.health(), is(-4));
        assertThat(player6.health(), is(1));

        assertThat(attack.getValue0(), not(EffectProcedure.none));
        assertThat(attack.getValue1(), is(AttackProcedure.none));
    }

    @Test
    public void defence_should_have_proper_result() {

        assertThat(player1.defence(), is(0));
        assertThat(player2.defence(), is(0));
    }

    @Test
    public void record_should_have_proper_result() {

        PlayerRecord record = player1.record();

        assertThat(record.name(), is("张三"));
        assertThat(record.health(), is(10));
        assertThat(record.armor(), is(ArmorRecord.none));
        assertThat(record.weapon(), is(WeaponRecord.none));
        assertThat(record.role(), is(Role.normal));
    }

    @Test
    public void suffer_should_have_proper_result() {

        PlayerRecord record0 = player1.record();
        assertThat(record0.health(), is(10));

        player1.suffer(5, Effect.none);

        PlayerRecord record1 = player1.record();
        assertThat(record1.health(), is(5));

        player1.suffer(5, Effect.none);

        PlayerRecord record2 = player1.record();
        assertThat(record2.health(), is(0));

        Flame flame = spy(Flame.create(2, 2));

        player2.suffer(1, flame);
        assertThat(player2.effect, is(flame));

        Toxin toxin = spy(Toxin.create(2, 2));

        player2.suffer(2, toxin);
        assertThat(player2.effect, is(toxin));
        assertThat(player2.effect.remain(), is(2));

        player2.suffer(2, toxin);
        assertThat(player2.effect, is(toxin));
        assertThat(player2.effect.remain(), is(4));
    }
}
