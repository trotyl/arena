package me.trotyl.arena.role;

import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.attribute.Toxic;
import me.trotyl.arena.equipment.LongWeapon;
import me.trotyl.arena.equipment.MediumWeapon;
import me.trotyl.arena.equipment.ShortWeapon;
import me.trotyl.arena.equipment.Weapon;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.MoveProcedure;
import org.javatuples.Triplet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AssassinTest {

    private Assassin assassin;

    private Weapon shortWeapon;
    private Weapon mediumWeapon;
    private Weapon longWeapon;

    @Before
    public void setUp() throws Exception {

        Random random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.5f);

        Attribute.config(random);

        assassin = Assassin.create("张三", 10, 5);

        shortWeapon = ShortWeapon.create("诸葛连弩", 3, Toxic.create(1, 2, 1.0f));
        mediumWeapon = MediumWeapon.create("雌雄双股剑", 4, 0, Toxic.create(2, 2, 1.0f));
        longWeapon = LongWeapon.create("方天画戟", 5, 0, Toxic.create(3, 2, 1.0f));
    }

    @After
    public void tearDown() throws Exception {
        Attribute.config(new Random());
    }

    @Test
    public void attack_should_have_proper_result_with_short_weapon() {

        assassin.equip(shortWeapon);

        Triplet<EffectProcedure, MoveProcedure, AttackProcedure> triplet = assassin.action(Player.create("王二", 10, 5), 1);
        AttackProcedure procedure = triplet.getValue2();

        assertThat(procedure.damage.genre, is(Genre.toxic));
    }

    @Test
    public void attack_should_have_proper_result_with_medium_weapon() {

        assassin.equip(mediumWeapon);

        Triplet<EffectProcedure, MoveProcedure, AttackProcedure> triplet = assassin.action(Player.create("王二", 10, 5), 1);
        AttackProcedure procedure = triplet.getValue2();

        assertThat(procedure.damage.genre, is(Genre.none));
    }

    @Test
    public void equip_should_have_proper_result_for_short_weapon() {

        assassin.equip(shortWeapon);

        assertThat(assassin.weapon, is(shortWeapon));
    }

    @Test
    public void equip_should_have_proper_result_for_medium_weapon() {

        assassin.equip(mediumWeapon);

        assertThat(assassin.weapon, is(mediumWeapon));
    }

    @Test(expected = IllegalArgumentException.class)
    public void equip_should_have_proper_result() {
        assassin.equip(longWeapon);
    }
}
