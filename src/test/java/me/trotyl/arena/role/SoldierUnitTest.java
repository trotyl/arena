package me.trotyl.arena.role;

import me.trotyl.arena.Armor;
import me.trotyl.arena.Weapon;
import me.trotyl.arena.attribute.Dizzy;
import me.trotyl.arena.attribute.Toxic;
import me.trotyl.arena.attribute.Striking;
import me.trotyl.arena.effect.Swoon;
import me.trotyl.arena.effect.Toxin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SoldierUnitTest {

    @Before
    public void setUp() throws Exception {
        Random random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);

        Striking.config(random);
        Toxic.config(random);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_be_more_aggressive_with_weapon() throws Exception {
        Soldier soldier = new Soldier("张三", 100, 20);
        soldier.equip(new Weapon("优质木棒", 50));

        Player player = new Player("李四", 100, 10);

        soldier.attack(player);

        assertThat(player.health, is(30));
    }

    @Test
    public void should_be_as_normal_player_without_weapon() throws Exception {
        Soldier soldier = new Soldier("张三", 100, 20);

        Player player = new Player("李四", 100, 10);

        soldier.attack(player);

        assertThat(player.health, is(80));
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
        Weapon weapon = new Weapon("我真剑", 10, new Striking(0, 1.0f));
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