package me.trotyl.arena.weapon;

import me.trotyl.arena.attribute.*;
import me.trotyl.arena.record.WeaponRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class WeaponTest {

    private Weapon weapon;

    @Before
    public void setUp() throws Exception {

        Random random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);

        Attribute.config(random);

        weapon = Weapon.create("优质木棒", 5, Length.medium);
    }

    @After
    public void tearDown() throws Exception {
        Attribute.config(new Random());
    }

    @Test
    public void raise_should_have_proper_result_without_attribute_for_single_attribute() {

        weapon.raise(Dizzy.create(1.0f));

        assertThat(weapon.attribute, instanceOf(Dizzy.class));
    }

    @Test
    public void raise_should_have_proper_result_without_attribute_for_multiple_attribute() {

        weapon.raise(Dizzy.create(1.0f), Flaming.create(2, 1, 1.0f), Striking.create(1.0f));

        assertThat(weapon.attribute, instanceOf(CompositeAttribute.class));

        CompositeAttribute composite = (CompositeAttribute) weapon.attribute;

        assertThat(composite.getFirst(), instanceOf(Dizzy.class));
        assertThat(composite.getSecond(), instanceOf(CompositeAttribute.class));

        CompositeAttribute innerComposite = (CompositeAttribute) composite.getSecond();

        assertThat(innerComposite.getFirst(), instanceOf(Flaming.class));
        assertThat(innerComposite.getSecond(), instanceOf(Striking.class));
    }

    @Test
    public void record_should_have_proper_result() {

        WeaponRecord record = weapon.record();

        assertThat(record.getName(), is("优质木棒"));
    }
}
