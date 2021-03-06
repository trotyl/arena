package me.trotyl.arena.role;

import me.trotyl.arena.Game;
import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.effect.Flame;
import me.trotyl.arena.equipment.Armor;
import me.trotyl.arena.equipment.MediumWeapon;
import me.trotyl.arena.equipment.Weapon;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.MoveProcedure;
import me.trotyl.arena.record.*;
import org.javatuples.Triplet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class SoldierTest {

    Soldier soldier0, soldier1;

    @Before
    public void setUp() throws Exception {

        soldier0 = Fighter.create("张三", 10, 5);
        soldier1 = Fighter.create("李四", 20, 8);

        Game.between(soldier0, soldier1);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void aggressivity_should_have_proper_result() {

        assertThat(soldier0.getAggressivity(), is(5));

        soldier0.equip(MediumWeapon.create("我真剑", 5, 0));
        assertThat(soldier0.getAggressivity(), is(10));
    }

    @Test
    public void attack_should_have_proper_result_without_effect() {

        Triplet<EffectProcedure, MoveProcedure, AttackProcedure> triplet = soldier0.action(soldier1);
        EffectProcedure effectProcedure = triplet.getValue0();
        AttackProcedure attackProcedure = triplet.getValue2();

        assertThat(effectProcedure, is(EffectProcedure.none));

        assertThat(attackProcedure.attacker.getName(), is("张三"));

        assertThat(attackProcedure.defender.getName(), is("李四"));
        assertThat(attackProcedure.defender.getHealth(), is(15));

        assertThat(attackProcedure.damage.genre, is(Genre.none));
        assertThat(attackProcedure.damage.extent, is(5));
    }

    @Test
    public void attack_should_have_proper_result_with_effect() {

        soldier0.effect = Flame.create(2, 10);
        soldier0.weapon = MediumWeapon.create("玄铁重剑", 3, 0);

        Triplet<EffectProcedure, MoveProcedure, AttackProcedure> triplet = soldier0.action(soldier1);
        EffectProcedure effectProcedure = triplet.getValue0();
        AttackProcedure attackProcedure = triplet.getValue2();

        assertThat(effectProcedure.host.getName(), is("张三"));
        assertThat(effectProcedure.damage.extent, is(2));

        assertThat(attackProcedure.attacker.getName(), is("张三"));

        assertThat(attackProcedure.defender.getName(), is("李四"));
        assertThat(attackProcedure.defender.getHealth(), is(12));

        assertThat(attackProcedure.damage.genre, is(Genre.none));
        assertThat(attackProcedure.damage.extent, is(8));
    }

    @Test
    public void attack_should_have_proper_invocation() {

        Soldier soldier2 = spy(soldier0);
        Soldier soldier3 = spy(soldier1);

        AggressiveAttribute attribute = mock(AggressiveAttribute.class);

        Effect effect = mock(Effect.class);
        when(effect.valid()).thenReturn(true);
        when(effect.record(any())).thenReturn(EffectRecord.create(Genre.flaming, Action.none));
        when(effect.take(soldier2)).thenReturn(DamageRecord.create(2, Genre.effect));
        when(effect.sway(soldier2, soldier3, attribute)).thenReturn(DamageRecord.create(3));

        soldier2.effect = effect;
        soldier2.weapon = spy(MediumWeapon.create("玄铁重剑", 3, 0, attribute));

        soldier2.action(soldier3);

        verifyZeroInteractions(attribute);

        InOrder inOrder = inOrder(soldier2, soldier3, effect);
        inOrder.verify(effect).take(soldier2);
        inOrder.verify(soldier2).record();
        inOrder.verify(effect).record(Action.attack);
        inOrder.verify(effect).sway(soldier2, soldier3, attribute);
        inOrder.verify(soldier2).record();
        inOrder.verify(soldier3).record();
        inOrder.verify(effect).valid();

        verifyNoMoreInteractions(effect);
    }

    @Test
    public void defence_should_have_proper_result() {

        assertThat(soldier0.getDefence(), is(0));

        soldier0.equip(Armor.create(5));
        assertThat(soldier0.getDefence(), is(5));
    }

    @Test
    public void equip_should_have_proper_result_for_first_call() {

        Weapon weapon = MediumWeapon.create("我真剑", 2, 0);
        Armor armor = Armor.create(3);

        assertThat(soldier0.weapon, is(Weapon.none));
        assertThat(soldier0.armor, is(Armor.none));

        soldier0.equip(weapon);
        assertThat(soldier0.weapon, is(weapon));
        assertThat(soldier0.armor, is(Armor.none));

        soldier0.equip(armor);
        assertThat(soldier0.weapon, is(weapon));
        assertThat(soldier0.armor, is(armor));
    }

    @Test
    public void equip_should_have_proper_result_for_multi_call() {

        Weapon weapon = MediumWeapon.create("我真剑", 2, 0);
        Armor armor = Armor.create(3);

        soldier0.equip(weapon);
        soldier0.equip(armor);

        Weapon newWeapon = MediumWeapon.create("优质木棒", 4, 0);
        Armor newArmor = Armor.create(4);

        soldier0.equip(newArmor);
        soldier0.equip(newWeapon);

        assertThat(soldier0.weapon, is(newWeapon));
        assertThat(soldier0.armor, is(newArmor));
    }

    @Test
    public void record_should_have_proper_result() {

        PlayerRecord record = soldier0.record();

        assertThat(record.getName(), is("张三"));
        assertThat(record.getHealth(), is(10));
        assertThat(record.getWeapon(), is(WeaponRecord.none));
        assertThat(record.getArmor(), is(ArmorRecord.none));

        soldier0.equip(MediumWeapon.create("我真剑", 5, 0));
        soldier0.equip(Armor.create(5));
        PlayerRecord newRecord = soldier0.record();

        assertThat(newRecord.getWeapon().getName(), is("我真剑"));
        assertThat(newRecord.getArmor().defence, is(5));
    }
}
