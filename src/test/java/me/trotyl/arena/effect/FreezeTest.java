package me.trotyl.arena.effect;

import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Player;
import org.javatuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class FreezeTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_not_attack_in_each_of_2_rounds() {
        Player player = new Player("张三", 10, 5);
        Freeze freeze = new Freeze(5);
        player.suffer(0, freeze);

        Player anotherPlayer = new Player("李四", 20, 8);

        Pair<EffectProcedure, AttackProcedure> pair0 = player.attack(anotherPlayer);
        AttackProcedure attack0 = pair0.getValue1();

        assertThat(attack0.damage, is(DamageRecord.none));

        Pair<EffectProcedure, AttackProcedure> pair1 = player.attack(anotherPlayer);
        AttackProcedure attack1 = pair1.getValue1();

        assertThat(attack1.damage.extent, is(5));

        Pair<EffectProcedure, AttackProcedure> pair2 = player.attack(anotherPlayer);
        AttackProcedure attack2 = pair2.getValue1();

        assertThat(attack2.damage, is(DamageRecord.none));

        Pair<EffectProcedure, AttackProcedure> pair3 = player.attack(anotherPlayer);
        AttackProcedure attack3 = pair3.getValue1();

        assertThat(attack3.damage.extent, is(5));
    }
}