package me.trotyl.arena.equipment;

import me.trotyl.arena.attribute.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;


public class ShortWeaponTest {

    private Weapon shortWeapon;

    @Before
    public void setUp() throws Exception {
        shortWeapon = ShortWeapon.create("青釭剑", 5, Toxic.create(3, 2, 1.0f));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAttribute_should_have_proper_result_when_none() {

        ShortWeapon newWeapon = ShortWeapon.create("青釭剑", 5);

        assertThat(newWeapon.getAttribute(), instanceOf(Carom.class));
    }

    @Test
    public void getAttribute_should_have_proper_result_when_not_none() {

        Attribute attribute = shortWeapon.getAttribute();

        assertThat(attribute, instanceOf(CompositeAttribute.class));

        CompositeAttribute composite = (CompositeAttribute) attribute;

        assertThat(composite.getFirst(), instanceOf(Carom.class));
        assertThat(composite.getSecond(), instanceOf(Toxic.class));
    }
}