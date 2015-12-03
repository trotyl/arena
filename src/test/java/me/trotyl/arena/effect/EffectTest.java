package me.trotyl.arena.effect;

import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.role.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;


public class EffectTest {

    private Effect effect;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() throws Exception {
        effect = Effect.none;

        player1 = spy(new Player("张三", 10, 5));
        player2 = spy(new Player("李四", 20, 8));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_be_taken_proper_effect() {
        DamageRecord damage = effect.take(player1);
        PlayerRecord player1Record = player1.record();
        PlayerRecord player2Record = player2.record();

        assertThat(damage, is(DamageRecord.none));

        assertThat(player1Record.health(), is(10));
        assertThat(player2Record.health(), is(20));
    }
}