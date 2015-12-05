package me.trotyl.arena.role;

import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.attribute.Toxic;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;
import org.javatuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class FighterTest {

    private Fighter fighter;

    private Weapon shortWeapon;
    private Weapon mediumWeapon;
    private Weapon longWeapon;

    @Before
    public void setUp() throws Exception {

        Random random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);

        Attribute.config(random);

        fighter = Fighter.create("张三", 10, 5);

        shortWeapon = Weapon.create("诸葛连弩", 3, Length.shorter, Toxic.create(1, 2, 2.0f));
        mediumWeapon = Weapon.create("雌雄双股剑", 4, Length.medium, Toxic.create(2, 2, 2.0f));
        longWeapon = Weapon.create("方天画戟", 5, Length.longer, Toxic.create(3, 2, 2.0f));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void attack_should_have_proper_result_with_medium_weapon() {

        fighter.equip(mediumWeapon);

        Pair<EffectProcedure, AttackProcedure> pair = fighter.attack(Player.create("王二", 10, 5));
        AttackProcedure procedure = pair.getValue1();

        assertThat(procedure.damage.genre, is(Genre.toxic));
    }

    @Test
    public void equip_should_have_proper_result() {

        fighter.equip(mediumWeapon);

        assertThat(fighter.weapon, is(mediumWeapon));

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