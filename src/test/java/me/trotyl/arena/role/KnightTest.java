package me.trotyl.arena.role;

import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.attribute.Toxic;
import me.trotyl.arena.equipment.LongWeapon;
import me.trotyl.arena.equipment.MediumWeapon;
import me.trotyl.arena.equipment.ShortWeapon;
import me.trotyl.arena.equipment.Weapon;
import me.trotyl.arena.procedure.ActionProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import org.javatuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class KnightTest {

    private Knight knight;

    private Weapon shortWeapon;
    private Weapon mediumWeapon;
    private Weapon longWeapon;

    @Before
    public void setUp() throws Exception {

        Random random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.5f);

        Attribute.config(random);

        knight = Knight.create("张三", 10, 5);

        shortWeapon = ShortWeapon.create("诸葛连弩", 3, Toxic.create(1, 2, 1.0f));
        mediumWeapon = MediumWeapon.create("雌雄双股剑", 4, 0, Toxic.create(2, 2, 1.0f));
        longWeapon = LongWeapon.create("方天画戟", 5, 0, Toxic.create(3, 2, 1.0f));
    }

    @After
    public void tearDown() throws Exception {
        Attribute.config(new Random());
    }

    @Test
    public void attack_should_have_proper_result_with_long_weapon() {

        knight.equip(longWeapon);

        Pair<EffectProcedure, ActionProcedure> pair = knight.action(Player.create("王二", 10, 5), 1);
        ActionProcedure procedure = pair.getValue1();

        assertThat(procedure.attack.damage.genre, is(Genre.toxic));
    }

    @Test
    public void attack_should_have_proper_result_with_medium_weapon() {

        knight.equip(mediumWeapon);

        Pair<EffectProcedure, ActionProcedure> pair = knight.action(Player.create("王二", 10, 5), 1);
        ActionProcedure procedure = pair.getValue1();

        assertThat(procedure.attack.damage.genre, is(Genre.none));
    }

    @Test(expected = IllegalArgumentException.class)
    public void equip_should_have_proper_result_for_short_weapon() {
        knight.equip(shortWeapon);
    }

    @Test
    public void equip_should_have_proper_result_for_medium_weapon() {

        knight.equip(mediumWeapon);

        assertThat(knight.weapon, is(mediumWeapon));
    }

    @Test
    public void equip_should_have_proper_result() {

        knight.equip(longWeapon);

        assertThat(knight.weapon, is(longWeapon));
    }
}
