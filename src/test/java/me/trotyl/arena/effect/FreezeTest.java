package me.trotyl.arena.effect;

import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.record.Action;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.role.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class FreezeTest {

    private Freeze freeze;
    private Player player1;
    private Player player2;
    private AggressiveAttribute attribute;

    @Before
    public void setUp() throws Exception {

        freeze = new Freeze(5);

        player1 = spy(Player.create("张三", 10, 5));
        player2 = spy(Player.create("李四", 20, 8));

        attribute = spy(new AggressiveAttribute(-1, -1.0f) {

            @Override
            public DamageRecord apply(Player attacker, Player defender, AggressiveAttribute next) {
                return DamageRecord.create(5);
            }
        });
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void record_should_have_proper_result() {

        EffectRecord record;

        record = freeze.record(Action.none);

        assertThat(record.genre, is(Genre.freezing));
        assertThat(record.remain, is(5));

        freeze.valid();
        record = freeze.record(Action.none);

        assertThat(record, is(EffectRecord.none));

        freeze.valid();
        record = freeze.record(Action.none);

        assertThat(record.genre, is(Genre.freezing));
        assertThat(record.remain, is(3));

        freeze.valid();
        record = freeze.record(Action.none);

        assertThat(record, is(EffectRecord.none));
    }

    @Test
    public void rein_should_have_proper_result() {

        int distance;

        distance = freeze.rein(player1);
        freeze.valid();

        assertThat(distance, is(0));

        distance = freeze.rein(player1);
        freeze.valid();

        assertThat(distance, is(1));

        distance = freeze.rein(player1);
        freeze.valid();

        assertThat(distance, is(0));

        distance = freeze.rein(player1);
        freeze.valid();

        assertThat(distance, is(1));
    }

    @Test
    public void sway_should_have_proper_result() {

        DamageRecord damage;

        damage = freeze.sway(player1, player2, attribute);
        freeze.valid();

        assertThat(damage, is(DamageRecord.none));

        damage = freeze.sway(player1, player2, attribute);
        freeze.valid();

        assertThat(damage, not(DamageRecord.none));
        assertThat(damage.extent, is(5));

        damage = freeze.sway(player1, player2, attribute);
        freeze.valid();

        assertThat(damage, is(DamageRecord.none));

        damage = freeze.sway(player1, player2, attribute);
        freeze.valid();

        assertThat(damage, not(DamageRecord.none));
        assertThat(damage.extent, is(5));
    }

    @Test
    public void sway_should_have_proper_invocation() {

        freeze.sway(player1, player2, attribute);
        freeze.valid();

        verifyZeroInteractions(player1);
        verifyZeroInteractions(player2);
        verifyZeroInteractions(attribute);

        freeze.sway(player1, player2, attribute);
        freeze.valid();

        InOrder inOrder = inOrder(attribute);
        inOrder.verify(attribute).apply(player1, player2, Attribute.normalAttack);

        freeze.sway(player1, player2, attribute);
        freeze.valid();

        verifyZeroInteractions(player1);
        verifyZeroInteractions(player2);
        verifyNoMoreInteractions(attribute);
    }

    @Test
    public void take_should_have_proper_result() {

        DamageRecord damage = freeze.take(player1);
        PlayerRecord player1Record = player1.record();
        PlayerRecord player2Record = player2.record();

        assertThat(damage, is(DamageRecord.none));

        assertThat(player1Record.getHealth(), is(10));
        assertThat(player2Record.getHealth(), is(20));
    }

    @Test
    public void take_should_have_proper_invocation() {

        freeze.take(player1);

        verifyZeroInteractions(player1);
    }

    @Test
    public void valid_should_have_proper_result() {

        assertThat(freeze.valid(), is(true));

        assertThat(freeze.valid(), is(true));

        assertThat(freeze.valid(), is(true));

        assertThat(freeze.valid(), is(true));

        assertThat(freeze.valid(), is(false));
    }
}
