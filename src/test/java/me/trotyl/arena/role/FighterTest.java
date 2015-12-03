package me.trotyl.arena.role;

import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class FighterTest {

    private Fighter fighter;

    @Before
    public void setUp() throws Exception {
        fighter = new Fighter("张三", 10, 5);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void equip_should_have_proper_result() {
        Weapon shortWeapon = new Weapon("诸葛连弩", 3, Length.shorter);
        Weapon middleWeapon = new Weapon("雌雄双股剑", 4, Length.medium);
        Weapon longWeapon = new Weapon("方天画戟", 5, Length.longer);

        fighter.equip(middleWeapon);

        assertThat(fighter.weapon, is(middleWeapon));

        try {
            fighter.equip(shortWeapon);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Fighter can only equip medium weapon!"));
        }

        try {
            fighter.equip(longWeapon);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Fighter can only equip medium weapon!"));
        }
    }
}