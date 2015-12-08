package me.trotyl.arena.role;

import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.effect.Flame;
import me.trotyl.arena.effect.Toxin;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.MoveProcedure;
import me.trotyl.arena.record.ArmorRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.record.WeaponRecord;
import org.javatuples.Triplet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;


public class PlayerTest {

    Player player0, player1;

    @Before
    public void setUp() throws Exception {

        player0 = Player.create("张三", 10, 5);
        player1 = Player.create("李四", 20, 8);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void aggressivity_should_have_proper_result() {

        assertThat(player0.getAggressivity(), is(5));
        assertThat(player1.getAggressivity(), is(8));
    }

    @Test
    public void alive_should_have_proper_result() {

        assertThat(player0.alive(), is(true));

        Player player2 = Player.create("王二", 1, 1);
        assertThat(player2.alive(), is(true));
    }

    @Test
    public void attack_should_have_proper_result_without_effect() {

        Triplet<EffectProcedure, MoveProcedure, AttackProcedure> triplet = player0.action(player1, 1);
        EffectProcedure effectProcedure = triplet.getValue0();
        AttackProcedure attackProcedure = triplet.getValue2();

        assertThat(effectProcedure, is(EffectProcedure.none));
        assertThat(attackProcedure.attacker.getName(), is("张三"));
        assertThat(attackProcedure.attackable.getName(), is("李四"));
        assertThat(attackProcedure.attackable.getHealth(), is(15));
        assertThat(attackProcedure.damage.genre, is(Genre.none));
        assertThat(attackProcedure.damage.extent, is(5));
    }

    @Test
    public void attack_should_have_proper_result_with_effect() {

        Player player2 = Player.create("王二", 1, 2);
        Player player3 = spy(Player.create("麻子", 1, 2));
        player2.suffer(0, Flame.create(5, 2));

        assertThat(player2.getHealth(), is(1));
        assertThat(player3.getHealth(), is(1));

        assertThat(player2.effect, instanceOf(Flame.class));

        Triplet<EffectProcedure, MoveProcedure, AttackProcedure> triplet = player2.action(player3, 1);

        assertThat(player2.getHealth(), is(-4));
        assertThat(player3.getHealth(), is(1));

        assertThat(triplet.getValue0(), not(EffectProcedure.none));
        assertThat(triplet.getValue2(), is(AttackProcedure.none));
    }

    @Test
    public void attack_should_have_proper_invocation() {

        Player player3 = spy(player0);
        Player player4 = spy(player1);

        player3.action(player4, 1);

        InOrder inOrder = inOrder(player3, player4);
        inOrder.verify(player3).record();
        inOrder.verify(player3).getAggressivity();
        inOrder.verify(player4).getDefence();
        inOrder.verify(player4).suffer(5, Effect.none);
        inOrder.verify(player3).record();
        inOrder.verify(player4).record();
    }

    @Test
    public void defence_should_have_proper_result() {

        assertThat(player0.getDefence(), is(0));
        assertThat(player1.getDefence(), is(0));
    }

    @Test
    public void record_should_have_proper_result() {

        PlayerRecord record = player0.record();

        assertThat(record.getName(), is("张三"));
        assertThat(record.getHealth(), is(10));
        assertThat(record.getArmor(), is(ArmorRecord.none));
        assertThat(record.getWeapon(), is(WeaponRecord.none));
        assertThat(record.getRole(), is(Role.normal));
    }

    @Test
    public void suffer_should_have_proper_result_for_health_reducing() {

        PlayerRecord record0 = player0.record();
        assertThat(record0.getHealth(), is(10));

        player0.suffer(5, Effect.none);

        PlayerRecord record1 = player0.record();
        assertThat(record1.getHealth(), is(5));

        player0.suffer(5, Effect.none);

        PlayerRecord record2 = player0.record();
        assertThat(record2.getHealth(), is(0));
    }

    @Test
    public void suffer_should_have_proper_result_for_effect_arising() {

        Flame flame = Flame.create(2, 2);

        player1.suffer(1, flame);
        assertThat(player1.effect, is(flame));

        Toxin toxin = Toxin.create(2, 2);

        player1.suffer(2, toxin);
        assertThat(player1.effect, is(toxin));
        assertThat(player1.effect.getRemain(), is(2));
    }

    @Test
    public void suffer_should_have_proper_result_for_effect_overlying() {

        player1.suffer(2, Toxin.create(2, 2));
        assertThat(player1.effect, instanceOf(Toxin.class));
        assertThat(player1.effect.getRemain(), is(2));

        player1.suffer(2, Toxin.create(2, 1));
        assertThat(player1.effect, instanceOf(Toxin.class));
        assertThat(player1.effect.getRemain(), is(3));
    }

    @Test
    public void suffer_should_have_proper_result_for_effect_replacing() {

        player1.suffer(2, Toxin.create(2, 2));
        assertThat(player1.effect, instanceOf(Toxin.class));
        assertThat(player1.effect.getRemain(), is(2));

        player1.suffer(2, Flame.create(2, 4));
        assertThat(player1.effect, instanceOf(Flame.class));
        assertThat(player1.effect.getRemain(), is(4));
    }
}
