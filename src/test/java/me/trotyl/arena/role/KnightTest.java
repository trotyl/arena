package me.trotyl.arena.role;

import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class KnightTest {

    private Knight knight;

    @Before
    public void setUp() throws Exception {
        knight = Knight.create("张三", 10, 5);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void equip_should_have_proper_result() {
        Weapon shortWeapon = Weapon.create("诸葛连弩", 3, Length.shorter);
        Weapon middleWeapon = Weapon.create("雌雄双股剑", 4, Length.medium);
        Weapon longWeapon = Weapon.create("方天画戟", 5, Length.longer);

        knight.equip(longWeapon);

        assertThat(knight.weapon, is(longWeapon));

        try {
            knight.equip(shortWeapon);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Knight can only equip long weapon!"));
        }

        try {
            knight.equip(middleWeapon);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Knight can only equip long weapon!"));
        }
    }
}