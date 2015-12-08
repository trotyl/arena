package me.trotyl.arena.equipment;

import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Counter;
import me.trotyl.arena.attribute.Toxic;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;


public class MediumWeaponTest {

    private Weapon mediumWeapon;

    @Before
    public void setUp() throws Exception {
        mediumWeapon = MediumWeapon.create("方天画戟", 5, 2, Toxic.create(3, 2, 1.0f));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAggressiveAttribute_should_have_proper_result() {

        Attribute attribute = mediumWeapon.getAggressiveAttribute();

        assertThat(attribute, instanceOf(Toxic.class));
    }

    @Test
    public void getDefensiveAttribute_should_have_proper_result_when_not_none() {

        Attribute attribute = mediumWeapon.getDefensiveAttribute();

        assertThat(attribute, instanceOf(Counter.class));
    }
}