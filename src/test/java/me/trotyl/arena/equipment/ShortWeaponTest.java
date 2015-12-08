package me.trotyl.arena.equipment;

import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.CompositeAttribute;
import me.trotyl.arena.attribute.Repel;
import me.trotyl.arena.attribute.Toxic;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;


public class ShortWeaponTest {

    private Weapon longWeapon;

    @Before
    public void setUp() throws Exception {
        longWeapon = LongWeapon.create("方天画戟", 5, 0, Toxic.create(3, 2, 1.0f));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAttribute_should_have_proper_result_when_none() {

        LongWeapon newWeapon = LongWeapon.create("方天画戟", 5, 1);

        assertThat(newWeapon.getAttribute(), instanceOf(Repel.class));
    }

    @Test
    public void getAttribute_should_have_proper_result_when_not_none() {

        Attribute attribute = longWeapon.getAttribute();

        assertThat(attribute, instanceOf(CompositeAttribute.class));

        CompositeAttribute composite = (CompositeAttribute) attribute;

        assertThat(composite.getFirst(), instanceOf(Repel.class));
        assertThat(composite.getSecond(), instanceOf(Toxic.class));
    }
}