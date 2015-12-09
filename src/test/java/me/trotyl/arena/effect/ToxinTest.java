package me.trotyl.arena.effect;

import me.trotyl.arena.Game;
import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.DefensiveAttribute;
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

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class ToxinTest {

    private Toxin toxin;
    private Player player1;
    private Player player2;
    private AggressiveAttribute attribute;

    @Before
    public void setUp() throws Exception {

        toxin = new Toxin(2, 2);

        player1 = spy(Player.create("张三", 10, 5));
        player2 = spy(Player.create("李四", 20, 8));
        Game.between(player1, player2);

        attribute = spy(new AggressiveAttribute(-1, 0.0f) {

            @Override
            public DamageRecord apply(Player a, Player d, AggressiveAttribute aa, DefensiveAttribute da) {
                return DamageRecord.none;
            }
        });
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void record_should_have_proper_result() {

        EffectRecord record = toxin.record(Action.none);

        assertThat(record.genre, is(Genre.toxic));
        assertThat(record.remain, is(2));
    }

    @Test
    public void sway_should_have_proper_result() {

        DamageRecord damage = toxin.sway(player1, player2, attribute);

        assertThat(damage, is(DamageRecord.none));
    }

    @Test
    public void sway_should_have_proper_invocation() {

        toxin.sway(player1, player2, attribute);

        InOrder inOrder = inOrder(attribute, player1, player2);
        inOrder.verify(player2).getDefensiveAttribute();
        inOrder.verify(attribute).apply(player1, player2, Attribute.normalAttack, Attribute.normalDefence);

        verifyNoMoreInteractions(attribute);
    }

    @Test
    public void take_should_have_proper_result() {

        DamageRecord damage = toxin.take(player1);

        PlayerRecord player1Record = player1.record();
        PlayerRecord player2Record = player2.record();

        assertThat(damage.genre, is(Genre.effect));
        assertThat(damage.extent, is(2));

        assertThat(player1Record.getHealth(), is(8));
        assertThat(player2Record.getHealth(), is(20));
    }

    @Test
    public void take_should_have_proper_invocation() {

        toxin.take(player1);

        InOrder inOrder = inOrder(player1);
        inOrder.verify(player1).suffer(argThat(instanceOf(DamageRecord.class)), eq(Effect.none));
    }

    @Test
    public void valid_should_have_proper_result() {

        assertThat(toxin.valid(), is(true));

        assertThat(toxin.valid(), is(false));
    }
}
