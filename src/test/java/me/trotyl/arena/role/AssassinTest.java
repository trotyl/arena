package me.trotyl.arena.role;

import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class AssassinTest {

    private Assassin assassin;

    @Before
    public void setUp() throws Exception {
        assassin = Assassin.create("张三", 10, 5);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void equip_should_have_proper_result() {

        Weapon shortWeapon = Weapon.create("诸葛连弩", 3, Length.shorter);
        Weapon middleWeapon = Weapon.create("雌雄双股剑", 4, Length.medium);
        Weapon longWeapon = Weapon.create("方天画戟", 5, Length.longer);

        assassin.equip(shortWeapon);

        assertThat(assassin.weapon, is(shortWeapon));

        try {
            assassin.equip(middleWeapon);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Assassin can only equip short weapon!"));
        }

        try {
            assassin.equip(longWeapon);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Assassin can only equip short weapon!"));
        }
    }
}