package me.trotyl.arena.attribute;

import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.effect.Flame;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.role.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class CompositeAttributeTest {

    private Random random;
    private CompositeAttribute composite;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() throws Exception {
        random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);

        Attribute.config(random);

        player1 = spy(Player.create("张三", 10, 5));
        player2 = spy(Player.create("李四", 20, 8));
    }

    @After
    public void tearDown() throws Exception {
        Attribute.config(new Random());
    }

    @Test
    public void apply_should_have_proper_result_for_different_attributes_when_first_work() {

        composite = new CompositeAttribute(Dizzy.create(1.0f), Flaming.create(3, 2, 1.0f));

        DamageRecord damage = composite.apply(player1, player2, Attribute.none);

        PlayerRecord player1Record = player1.record();
        PlayerRecord player2Record = player2.record();

        assertThat(damage.genre, is(Genre.dizzy));
        assertThat(damage.extent, is(5));

        assertThat(player1Record.getHealth(), is(10));
        assertThat(player2Record.getHealth(), is(15));
    }

    @Test
    public void apply_should_have_proper_result_for_different_attributes_when_first_not_work() {

        composite = new CompositeAttribute(Dizzy.create(0.0f), Flaming.create(3, 2, 1.0f));

        DamageRecord damage = composite.apply(player1, player2, Attribute.none);

        PlayerRecord player1Record = player1.record();
        PlayerRecord player2Record = player2.record();

        assertThat(damage.genre, is(Genre.flaming));
        assertThat(damage.extent, is(5));

        assertThat(player1Record.getHealth(), is(10));
        assertThat(player2Record.getHealth(), is(15));
    }

    @Test
    public void apply_should_have_proper_result_for_different_attributes_when_neither_work() {

        composite = new CompositeAttribute(Dizzy.create(0.0f), Flaming.create(3, 2, 0.0f));

        DamageRecord damage = composite.apply(player1, player2, Attribute.none);

        PlayerRecord player1Record = player1.record();
        PlayerRecord player2Record = player2.record();

        assertThat(damage.genre, is(Genre.none));
        assertThat(damage.extent, is(5));

        assertThat(player1Record.getHealth(), is(10));
        assertThat(player2Record.getHealth(), is(15));
    }

    @Test
    public void apply_should_have_proper_result_for_same_attributes_when_first_work() {

        composite = new CompositeAttribute(Flaming.create(5, 6, 1.0f), Flaming.create(3, 2, 1.0f));

        composite.apply(player1, player2, Attribute.none);

        assertThat(player2.getEffect(), instanceOf(Flame.class));
        assertThat(player2.getEffect().getRemain(), is(6));
    }

    @Test
    public void apply_should_have_proper_result_for_same_attributes_when_first_not_work() {

        composite = new CompositeAttribute(Flaming.create(5, 6, 0.0f), Flaming.create(3, 2, 1.0f));

        composite.apply(player1, player2, Attribute.none);

        assertThat(player2.getEffect(), instanceOf(Flame.class));
        assertThat(player2.getEffect().getRemain(), is(2));
    }

    @Test
    public void apply_should_have_proper_result_for_same_attributes_when_neither_work() {

        composite = new CompositeAttribute(Flaming.create(5, 6, 0.0f), Flaming.create(3, 2, 0.0f));

        composite.apply(player1, player2, Attribute.none);

        assertThat(player2.getEffect(), is(Effect.none));
    }
}
