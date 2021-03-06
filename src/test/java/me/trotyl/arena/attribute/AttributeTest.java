package me.trotyl.arena.attribute;

import me.trotyl.arena.Game;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.role.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Random;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class AttributeTest {

    private AggressiveAttribute attribute;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() throws Exception {

        Random random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);

        Attribute.config(random);

        attribute = Attribute.normalAttack;

        player1 = spy(Player.create("张三", 10, 5));
        player2 = spy(Player.create("李四", 20, 8));
        Game.between(player1, player2);
    }

    @After
    public void tearDown() throws Exception {
        Attribute.config(new Random());
    }

    @Test
    public void compose0_should_have_proper_result_for_none_and_empty_list() {
        assertThat(AggressiveAttribute.compose(Attribute.normalAttack, emptyList()), is(Attribute.normalAttack));
    }

    @Test
    public void compose0_should_have_proper_result_for_none_and_single_list() {

        Dizzy dizzy = Dizzy.create(0.5f);

        assertThat(AggressiveAttribute.compose(Attribute.normalAttack, singletonList(dizzy)), is(dizzy));
    }

    @Test
    public void compose0_should_have_proper_result_for_none_and_2_element_list() {

        Dizzy dizzy = Dizzy.create(0.5f);
        Striking striking = Striking.create(0.5f);

        Attribute attribute = AggressiveAttribute.compose(Attribute.normalAttack, asList(dizzy, striking));

        assertThat(attribute, instanceOf(CompositeAttribute.class));

        CompositeAttribute composite = (CompositeAttribute) attribute;

        assertThat(composite.getFirst(), is(dizzy));
        assertThat(composite.getSecond(), is(striking));
    }

    @Test
    public void compose0_should_have_proper_result_for_none_and_multiple_element_list() {

        Dizzy dizzy = Dizzy.create(0.5f);
        Striking striking = Striking.create(0.5f);
        Flaming flaming = Flaming.create(2, 2, 0.5f);

        Attribute attribute = AggressiveAttribute.compose(Attribute.normalAttack, asList(dizzy, striking, flaming));

        assertThat(attribute, instanceOf(CompositeAttribute.class));

        CompositeAttribute composite = (CompositeAttribute) attribute;

        assertThat(composite.getFirst(), is(dizzy));
        assertThat(composite.getSecond(), instanceOf(CompositeAttribute.class));

        CompositeAttribute innerComposite = (CompositeAttribute) composite.getSecond();

        assertThat(innerComposite.getFirst(), is(striking));
        assertThat(innerComposite.getSecond(), is(flaming));
    }

    @Test
    public void compose0_should_have_proper_result_for_not_none_and_empty_list() {

        Dizzy dizzy = Dizzy.create(0.5f);

        assertThat(AggressiveAttribute.compose(dizzy, emptyList()), is(dizzy));
    }

    @Test
    public void compose0_should_have_proper_result_for_not_none_and_not_empty_list() {

        Dizzy dizzy = Dizzy.create(0.5f);
        Striking striking = Striking.create(0.5f);

        Attribute attribute = AggressiveAttribute.compose(dizzy, singletonList(striking));

        assertThat(attribute, instanceOf(CompositeAttribute.class));

        CompositeAttribute composite = (CompositeAttribute) attribute;

        assertThat(composite.getFirst(), is(dizzy));
        assertThat(composite.getSecond(), is(striking));
    }

    @Test
    public void compose1_should_have_proper_result_for_none_and_empty_list() {
        assertThat(AggressiveAttribute.compose(emptyList()), is(Attribute.normalAttack));
    }

    @Test
    public void compose1_should_have_proper_result_for_none_and_single_list() {

        Dizzy dizzy = Dizzy.create(0.5f);

        assertThat(AggressiveAttribute.compose(singletonList(dizzy)), is(dizzy));
    }

    @Test
    public void compose1_should_have_proper_result_for_none_and_2_element_list() {

        Dizzy dizzy = Dizzy.create(0.5f);
        Striking striking = Striking.create(0.5f);

        Attribute attribute = AggressiveAttribute.compose(asList(dizzy, striking));

        assertThat(attribute, instanceOf(CompositeAttribute.class));

        CompositeAttribute composite = (CompositeAttribute) attribute;

        assertThat(composite.getFirst(), is(dizzy));
        assertThat(composite.getSecond(), is(striking));
    }

    @Test
    public void compose1_should_have_proper_result_for_none_and_multiple_element_list() {

        Dizzy dizzy = Dizzy.create(0.5f);
        Striking striking = Striking.create(0.5f);
        Flaming flaming = Flaming.create(2, 2, 0.5f);

        Attribute attribute = AggressiveAttribute.compose(asList(dizzy, striking, flaming));

        assertThat(attribute, instanceOf(CompositeAttribute.class));

        CompositeAttribute composite = (CompositeAttribute) attribute;

        assertThat(composite.getFirst(), is(dizzy));
        assertThat(composite.getSecond(), instanceOf(CompositeAttribute.class));

        CompositeAttribute innerComposite = (CompositeAttribute) composite.getSecond();

        assertThat(innerComposite.getFirst(), is(striking));
        assertThat(innerComposite.getSecond(), is(flaming));
    }

    @Test
    public void apply_should_have_proper_result() {

        DamageRecord damage = attribute.apply(player1, player2, Attribute.normalAttack, Attribute.normalDefence);

        PlayerRecord player1Record = player1.record();
        PlayerRecord player2Record = player2.record();

        assertThat(damage.genre, is(Genre.none));
        assertThat(damage.extent, is(5));

        assertThat(player1Record.getHealth(), is(10));
        assertThat(player2Record.getHealth(), is(15));
    }

    @Test
    public void apply_should_have_proper_invocation() {

        attribute.apply(player1, player2, Attribute.normalAttack, Attribute.normalDefence);

        InOrder inOrder = inOrder(player1, player2);
        inOrder.verify(player1).getAggressivity();
        inOrder.verify(player2).getDefence();
        inOrder.verify(player2).suffer(argThat(instanceOf(DamageRecord.class)), eq(Effect.none));
    }
}
