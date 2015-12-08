package me.trotyl.arena.effect;

import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.record.Action;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.role.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verifyZeroInteractions;


public class SwoonTest {

    private Swoon swoon;
    private Player player1;
    private Player player2;
    private AggressiveAttribute attribute;

    @Before
    public void setUp() throws Exception {

        swoon = new Swoon(2);

        player1 = spy(Player.create("张三", 10, 5));
        player2 = spy(Player.create("李四", 20, 8));

        attribute = spy(new AggressiveAttribute(-1, 0.0f) {

            @Override
            public DamageRecord apply(Player attacker, Player defender, AggressiveAttribute next) {
                return DamageRecord.none;
            }
        });
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void record_should_have_proper_result() {

        EffectRecord record = swoon.record(Action.none);

        assertThat(record.genre, is(Genre.dizzy));
        assertThat(record.remain, is(2));
    }

    @Test
    public void rein_should_have_proper_result() {

        int distance;

        distance = swoon.rein(player1);
        swoon.sway(player1, player2, attribute);

        assertThat(distance, is(0));
    }

    @Test
    public void sway_should_have_proper_result() {

        DamageRecord damage = swoon.sway(player1, player2, attribute);

        assertThat(damage, is(DamageRecord.none));
    }

    @Test
    public void sway_should_have_proper_invocation() {

        swoon.sway(player1, player2, attribute);

        verifyZeroInteractions(player1);
        verifyZeroInteractions(player2);
        verifyZeroInteractions(attribute);

        swoon.sway(player1, player2, attribute);

        verifyZeroInteractions(player1);
        verifyZeroInteractions(player2);
        verifyZeroInteractions(attribute);
    }

    @Test
    public void take_should_have_proper_result() {

        DamageRecord damage = swoon.take(player1);
        PlayerRecord player1Record = player1.record();
        PlayerRecord player2Record = player2.record();

        assertThat(damage, is(DamageRecord.none));

        assertThat(player1Record.getHealth(), is(10));
        assertThat(player2Record.getHealth(), is(20));
    }

    @Test
    public void take_should_have_proper_invocation() {

        swoon.take(player1);

        verifyZeroInteractions(player1);
    }

    @Test
    public void valid_should_have_proper_result() {

        assertThat(swoon.valid(), is(true));

        assertThat(swoon.valid(), is(false));
    }
}
