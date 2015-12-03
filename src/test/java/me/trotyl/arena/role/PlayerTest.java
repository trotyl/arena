package me.trotyl.arena.role;

import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.effect.Fire;
import me.trotyl.arena.effect.Toxin;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.*;
import org.javatuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class PlayerTest {

    Player player1, player2;

    @Before
    public void setUp() throws Exception {
        player1 = new Player("张三", 10, 5);
        player2 = new Player("李四", 20, 8);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void alive_should_have_proper_result() {
        assertThat(player1.alive(), is(true));

        Player player3 = new Player("王二", 1, 1);
        assertThat(player3.alive(), is(true));

        Player player4 = new Player("麻子", 0, 1);
        assertThat(player4.alive(), is(false));
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

        Fire fire = spy(new Fire(2, 2));

        player2.suffer(1, fire);
        assertThat(player2.effect, is(fire));

        Toxin toxin = spy(new Toxin(2, 2));

        player2.suffer(2, toxin);
        assertThat(player2.effect, is(toxin));
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
    public void defence_should_have_proper_result() {
        assertThat(player1.defence(), is(0));
        assertThat(player2.defence(), is(0));
    }

    @Test
    public void aggressivity_should_have_proper_result() {
        assertThat(player1.aggressivity(), is(5));
        assertThat(player2.aggressivity(), is(8));
    }

    @Test
    public void attack_should_have_proper_result() {
        Player player3 = spy(player1);
        Player player4 = spy(player2);

        Pair<EffectProcedure, AttackProcedure> pair = player3.attack(player4);
        EffectProcedure effectProcedure = pair.getValue0();
        AttackProcedure attackProcedure = pair.getValue1();

        assertThat(effectProcedure.attackable.name(), is("张三"));
        assertThat(effectProcedure.attackable.health(), is(10));
        assertThat(effectProcedure.damage, is(DamageRecord.none));
        assertThat(effectProcedure.effect, is(EffectRecord.none));
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
    }
}