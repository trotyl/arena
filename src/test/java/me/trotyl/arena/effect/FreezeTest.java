package me.trotyl.arena.effect;

import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;
import me.trotyl.arena.role.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class FreezeTest {

    private Freeze freeze;
    private Player player1;
    private Player player2;
    private Attribute attribute;

    @Before
    public void setUp() throws Exception {

        freeze = new Freeze(2);

        player1 = spy(Player.create("张三", 10, 5));
        player2 = spy(Player.create("李四", 20, 8));

        attribute = spy(new Attribute(-1, 0.0f) {
            @Override
            public DamageRecord apply(Attacker attacker, Attackable attackable) {
                return DamageRecord.none;
            }
        });
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void record_should_have_proper_result() {

        EffectRecord record = freeze.record();

        assertThat(record.type, is(Type.freeze));
        assertThat(record.remain, is(2));
    }

    @Test
    public void sway_should_have_proper_result() {

        DamageRecord damage = freeze.sway(player1, player2, attribute);

        assertThat(damage, is(DamageRecord.none));
    }

    @Test
    public void sway_should_have_proper_invocation() {

        freeze.sway(player1, player2, attribute);

        verifyZeroInteractions(player1);
        verifyZeroInteractions(player2);
        verifyZeroInteractions(attribute);

        freeze.sway(player1, player2, attribute);

        InOrder inOrder = inOrder(attribute);
        inOrder.verify(attribute).apply(player1, player2);

        freeze.sway(player1, player2, attribute);

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

        assertThat(player1Record.health(), is(10));
        assertThat(player2Record.health(), is(20));
    }

    @Test
    public void take_should_have_proper_invocation() {

        freeze.take(player1);

        verifyZeroInteractions(player1);
    }

    @Test
    public void valid_should_have_proper_result() {

        assertThat(freeze.valid(), is(true));

        freeze.sway(player1, player2, attribute);
        assertThat(freeze.valid(), is(true));

        freeze.sway(player1, player2, attribute);
        assertThat(freeze.valid(), is(false));
    }
}