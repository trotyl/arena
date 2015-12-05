package me.trotyl.arena.weapon;

import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Dizzy;
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
    public void launch_should_have_proper_result() {

        weapon.raise(Dizzy.create(1.0f));

        Attribute attribute = weapon.launch();

        assertThat(attribute, instanceOf(Dizzy.class));
    }

    @Test
    public void record_should_have_proper_result() {

        WeaponRecord record = weapon.record();

        assertThat(record.getName(), is("优质木棒"));
    }
}