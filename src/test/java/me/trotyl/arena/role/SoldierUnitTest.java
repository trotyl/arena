package me.trotyl.arena.role;

import me.trotyl.arena.Armor;
import me.trotyl.arena.Weapon;
import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Strike;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SoldierUnitTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_be_more_aggressive_with_weapon() throws Exception {
        Soldier soldier = new Soldier("张三", 100, 20);
        Weapon weapon = new Weapon("优质木棒", 50);
        soldier.equip(weapon);

        Player player = new Player("李四", 100, 10);

        soldier.attack(player);

        assertThat(player.health, is(30));
    }

    @Test
    public void should_be_more_defensive_with_weapon() throws Exception {
        Soldier soldier = new Soldier("张三", 100, 20);
        Armor armor = new Armor(10);
        soldier.equip(armor);

        Player player = new Player("李四", 100, 20);

        player.attack(soldier);

        assertThat(soldier.health, is(90));
    }


    @Test
    public void should_have_3_times_damage_with_strike() throws Exception {
        Soldier soldier = new Soldier("张三", 100, 20);
        Weapon weapon = new Weapon("我真剑", 10);
        Random random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);
        Attribute strike = new Strike(random, 1);
        weapon.with(strike);
        soldier.equip(weapon);

        Player player = new Player("李四", 100, 10);

        soldier.attack(player);

        assertThat(player.health, is(10));
    }
}