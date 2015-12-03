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


public class SwoonTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_not_attack_in_2_rounds() {
        Player player = new Player("张三", 10, 5);
        Swoon swoon = new Swoon(2);
        player.suffer(0, swoon);

        Player anotherPlayer = new Player("李四", 20, 8);

        Pair<EffectProcedure, AttackProcedure> pair = player.attack(anotherPlayer);
        AttackProcedure attack = pair.getValue1();

        assertThat(attack.damage, is(DamageRecord.none));
    }
}