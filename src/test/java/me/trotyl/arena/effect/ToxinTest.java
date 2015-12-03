package me.trotyl.arena.effect;

import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.role.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ToxinTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_produce_toxin_effect() {
        Player player = new Player("张三", 10, 5);
        Toxin toxin = new Toxin(2, 2);

        DamageRecord damageRecord = toxin.take(player);
        PlayerRecord playerRecord = player.record();

        assertThat(damageRecord.extent, is(2));
        assertThat(playerRecord.health(), is(8));
    }

    @Test
    public void should_gone_after_limits_effect() {
        Player player = new Player("张三", 10, 5);
        Toxin toxin = new Toxin(2, 2);
        player.suffer(0, toxin);

        Player anotherPlayer = new Player("李四", 20, 8);

        player.attack(anotherPlayer);
        EffectProcedure procedure0 = player.attack(anotherPlayer).getValue0();

        DamageRecord damageRecord0 = procedure0.damage;
        PlayerRecord playerRecord0 = player.record();

        assertThat(damageRecord0.extent, is(2));
        assertThat(playerRecord0.health(), is(6));

        EffectProcedure procedure1 = player.attack(anotherPlayer).getValue0();

        DamageRecord damageRecord1 = procedure1.damage;
        PlayerRecord playerRecord1 = player.record();

        assertThat(damageRecord1.extent, is(0));
        assertThat(playerRecord1.health(), is(6));
    }
}