package me.trotyl.arena.role;

import me.trotyl.arena.Armor;
import me.trotyl.arena.Weapon;
import me.trotyl.arena.attribute.*;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.effect.Swoon;
import me.trotyl.arena.effect.Toxin;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import org.javatuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Random;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class SoldierTest {

    Soldier soldier0, soldier1;

    @Before
    public void setUp() throws Exception {
        soldier0 = new Soldier("张三", 10, 5);
        soldier1 = new Soldier("李四", 20, 8);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void equip_should_have_proper_result() {
        Weapon weapon = new Weapon("我真剑", 2);
        Armor armor = new Armor(3);

        assertThat(soldier0.weapon, is(Weapon.none));
        assertThat(soldier0.armor, is(Armor.none));

        soldier0.equip(weapon);
        assertThat(soldier0.weapon, is(weapon));
        assertThat(soldier0.armor, is(Armor.none));

        soldier0.equip(armor);
        assertThat(soldier0.weapon, is(weapon));
        assertThat(soldier0.armor, is(armor));

        Weapon newWeapon = new Weapon("优质木棒", 4);
        Armor newArmor = new Armor(4);

        soldier0.equip(newArmor);
        assertThat(soldier0.weapon, is(weapon));
        assertThat(soldier0.armor, is(newArmor));

        soldier0.equip(newWeapon);
        assertThat(soldier0.weapon, is(newWeapon));
        assertThat(soldier0.armor, is(newArmor));
    }

    @Test
    public void attack_should_have_proper_result() {
        Soldier soldier2 = spy(soldier0);
        Soldier soldier3 = spy(soldier1);
        Attribute attribute = mock(Attribute.class);
        Effect effect = mock(Effect.class);
        when(effect.valid()).thenReturn(true);
        when(effect.record()).thenReturn(EffectRecord.none);
        when(effect.take(soldier2)).thenReturn(new DamageRecord(2));
        when(effect.sway(soldier2, soldier3, attribute)).thenReturn(new DamageRecord(3));
        soldier2.effect = effect;
        soldier2.weapon = spy(new Weapon("玄铁重剑", 3, attribute));

        Pair<EffectProcedure, AttackProcedure> pair = soldier2.attack(soldier3);
        EffectProcedure effectProcedure = pair.getValue0();
        AttackProcedure attackProcedure = pair.getValue1();

        assertThat(effectProcedure.attackable.name(), is("张三"));
        assertThat(effectProcedure.attackable.health(), is(10));
        assertThat(effectProcedure.damage.extent, is(2));
        assertThat(attackProcedure.attacker.name(), is("张三"));
        assertThat(attackProcedure.attackable.name(), is("李四"));
        assertThat(attackProcedure.attackable.health(), is(20));
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
    public void aggressivity_should_have_proper_result() {
        assertThat(soldier0.aggressivity(), is(5));

        soldier0.equip(new Weapon("我真剑", 5));
        assertThat(soldier0.aggressivity(), is(10));
    }

    @Test
    public void should_be_more_defensive_with_armor() throws Exception {
        Soldier soldier = new Soldier("张三", 100, 20);
        soldier.equip(new Armor(10));

        Player player = new Player("李四", 100, 20);

        player.attack(soldier);

        assertThat(soldier.health, is(90));
    }

    @Test
    public void should_be_as_normal_player_without_armor() throws Exception {
        Soldier soldier = new Soldier("张三", 100, 20);

        Player player = new Player("李四", 100, 20);

        player.attack(soldier);

        assertThat(soldier.health, is(80));
    }


    @Test
    public void should_have_3_times_damage_with_strike() throws Exception {
        Random random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);
        Weapon weapon = new Weapon("我真剑", 10, new Striking(1.0f));
        Soldier soldier = new Soldier("张三", 100, 20);
        soldier.equip(weapon);

        Player player = new Player("李四", 100, 10);

        soldier.attack(player);

        assertThat(player.health, is(10));
    }

    @Test
    public void should_produce_toxin_effect_with_noxious() throws Exception {

        Weapon weapon = new Weapon("我真剑", 10, new Toxic(2, 2, 1.0f));
        Soldier soldier = new Soldier("张三", 100, 20);
        soldier.equip(weapon);

        Player player = new Player("李四", 100, 10);

        soldier.attack(player);

        assertThat(player.health, is(70));
        assertThat(player.effect, instanceOf(Toxin.class));
    }

    @Test
    public void should_produce_toxin_effect_with_dizzy() throws Exception {

        Weapon weapon = new Weapon("我真剑", 10, new Dizzy(1.0f));
        Soldier soldier = new Soldier("张三", 100, 20);
        soldier.equip(weapon);

        Player player = new Player("李四", 100, 10);

        soldier.attack(player);

        assertThat(player.health, is(70));
        assertThat(player.effect, instanceOf(Swoon.class));
    }
}