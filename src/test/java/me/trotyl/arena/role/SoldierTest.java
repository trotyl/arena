package me.trotyl.arena.role;

import me.trotyl.arena.armor.Armor;
import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.effect.Type;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.*;
import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;
import org.javatuples.Pair;
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

        soldier0 = Soldier.create("张三", 10, 5);
        soldier1 = Soldier.create("李四", 20, 8);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void aggressivity_should_have_proper_result() {

        assertThat(soldier0.getAggressivity(), is(5));

        soldier0.equip(Weapon.create("我真剑", 5, Length.none));
        assertThat(soldier0.getAggressivity(), is(10));
    }

    @Test
    public void attack_should_have_proper_result() {

        Soldier soldier2 = spy(soldier0);
        Soldier soldier3 = spy(soldier1);

        Attribute attribute = mock(Attribute.class);

        Effect effect = mock(Effect.class);
        when(effect.valid()).thenReturn(true);
        when(effect.record()).thenReturn(EffectRecord.create(Type.flame));
        when(effect.take(soldier2)).thenReturn(DamageRecord.create(2, Genre.effect));
        when(effect.sway(soldier2, soldier3, attribute)).thenReturn(DamageRecord.create(3));

        soldier2.effect = effect;
        soldier2.weapon = spy(Weapon.create("玄铁重剑", 3, Length.none, attribute));

        Pair<EffectProcedure, AttackProcedure> pair = soldier2.attack(soldier3);
        EffectProcedure effectProcedure = pair.getValue0();
        AttackProcedure attackProcedure = pair.getValue1();

        assertThat(effectProcedure.attackable.getName(), is("张三"));
        assertThat(effectProcedure.damage.extent, is(2));

        assertThat(attackProcedure.attacker.getName(), is("张三"));

        assertThat(attackProcedure.attackable.getName(), is("李四"));
        assertThat(attackProcedure.attackable.getHealth(), is(20));

        assertThat(attackProcedure.damage.genre, is(Genre.none));
        assertThat(attackProcedure.damage.extent, is(3));

        verifyZeroInteractions(attribute);

        InOrder inOrder = inOrder(soldier2, soldier3, effect);
        inOrder.verify(effect).take(soldier2);
        inOrder.verify(soldier2).record();
        inOrder.verify(effect).record();
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
    public void equip_should_have_proper_result() {

        Weapon weapon = Weapon.create("我真剑", 2, Length.none);
        Armor armor = Armor.create(3);

        assertThat(soldier0.weapon, is(Weapon.none));
        assertThat(soldier0.armor, is(Armor.none));

        soldier0.equip(weapon);
        assertThat(soldier0.weapon, is(weapon));
        assertThat(soldier0.armor, is(Armor.none));

        soldier0.equip(armor);
        assertThat(soldier0.weapon, is(weapon));
        assertThat(soldier0.armor, is(armor));

        Weapon newWeapon = Weapon.create("优质木棒", 4, Length.none);
        Armor newArmor = Armor.create(4);

        soldier0.equip(newArmor);
        assertThat(soldier0.weapon, is(weapon));
        assertThat(soldier0.armor, is(newArmor));

        soldier0.equip(newWeapon);
        assertThat(soldier0.weapon, is(newWeapon));
        assertThat(soldier0.armor, is(newArmor));
    }

    @Test
    public void record_should_have_proper_result() {

        PlayerRecord record = soldier0.record();

        assertThat(record.getName(), is("张三"));
        assertThat(record.getHealth(), is(10));
        assertThat(record.getRole(), is(Role.soldier));
        assertThat(record.getWeapon(), is(WeaponRecord.none));
        assertThat(record.getArmor(), is(ArmorRecord.none));

        soldier0.equip(Weapon.create("我真剑", 5, Length.none));
        soldier0.equip(Armor.create(5));
        PlayerRecord newRecord = soldier0.record();

        assertThat(newRecord.getWeapon().getName(), is("我真剑"));
        assertThat(newRecord.getArmor().defence, is(5));
    }
}