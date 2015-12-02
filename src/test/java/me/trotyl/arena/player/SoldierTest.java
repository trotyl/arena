package me.trotyl.arena.player;

import me.trotyl.arena.Weapon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class SoldierTest {

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

        assertThat(player.health(), is(30));
    }
}