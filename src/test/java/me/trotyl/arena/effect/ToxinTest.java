package me.trotyl.arena.effect;

import me.trotyl.arena.record.EffectRecord;
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

        EffectRecord effectRecord = toxin.take(player);
        PlayerRecord playerRecord = player.record();

        assertThat(effectRecord.damage.extent, is(2));
        assertThat(playerRecord.health(), is(8));
    }
}