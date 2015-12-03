package me.trotyl.arena;

import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;
import me.trotyl.arena.role.Player;
import me.trotyl.arena.role.Soldier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class FormatterUnitTest {

    private Formatter formatter;
    private Player player;
    private Player anotherPlayer;
    private Soldier soldierWithoutEquipment;
    private Soldier soldierWithWeapon;
    private Soldier soldierWithArmor;
    private Soldier soldierWithWeaponAndArmor;

    private String formattedAttack(Attacker attacker, Attackable attackable) {
        AttackProcedure attack = attacker.attack(attackable);
        return formatter.formatAttack(attack);
    }

    @Before
    public void setUp() throws Exception {
        formatter = new Formatter();

        player = new Player("张三", 10, 5);
        anotherPlayer = new Player("李四", 20, 8);
        soldierWithoutEquipment = new Soldier("李四", 20, 8);
        soldierWithWeapon = new Soldier("李四", 20, 8);
        soldierWithWeapon.equip(new Weapon("优质木棒", 5));
        soldierWithArmor = new Soldier("李四", 20, 8);
        soldierWithArmor.equip(new Armor(3));
        soldierWithWeaponAndArmor = new Soldier("李四", 20, 8);
        soldierWithWeaponAndArmor.equip(new Weapon("优质木棒", 5));
        soldierWithWeaponAndArmor.equip(new Armor(3));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_work_of_attack_between_normal_players() {
        String result = formattedAttack(player, anotherPlayer);

        assertThat(result, is("普通人张三攻击了普通人李四, 李四受到了5点伤害, 李四剩余生命: 15"));
    }

    @Test
    public void should_work_of_attack_between_normal_player_and_soldier_without_equipment() {
        String result = formattedAttack(player, soldierWithoutEquipment);

        assertThat(result, is("普通人张三攻击了战士李四, 李四受到了5点伤害, 李四剩余生命: 15"));
    }

    @Test
    public void should_work_of_attack_between_normal_player_and_soldier_with_weapon_but_no_armor() {
        String result = formattedAttack(player, soldierWithWeapon);

        assertThat(result, is("普通人张三攻击了战士李四, 李四受到了5点伤害, 李四剩余生命: 15"));
    }

    @Test
    public void should_work_of_attack_between_normal_player_and_soldier_with_armor_but_no_weapon() {
        String result = formattedAttack(player, soldierWithArmor);

        assertThat(result, is("普通人张三攻击了战士李四, 李四受到了2点伤害, 李四剩余生命: 18"));
    }

    @Test
    public void should_work_of_attack_between_normal_player_and_soldier_with_weapon_and_armor() {
        String result = formattedAttack(player, soldierWithWeaponAndArmor);

        assertThat(result, is("普通人张三攻击了战士李四, 李四受到了2点伤害, 李四剩余生命: 18"));
    }

    @Test
    public void should_work_of_attack_between_soldier_without_equipment_and_normal_player() {
        String result = formattedAttack(soldierWithoutEquipment, player);

        assertThat(result, is("战士李四攻击了普通人张三, 张三受到了8点伤害, 张三剩余生命: 2"));
    }

    @Test
    public void should_work_of_attack_between_soldier_with_weapon_but_no_armor_and_normal_player() {
        String result = formattedAttack(soldierWithWeapon, player);

        assertThat(result, is("战士李四用优质木棒攻击了普通人张三, 张三受到了13点伤害, 张三剩余生命: -3"));
    }

    @Test
    public void should_work_of_attack_between_soldier_with_weapon_and_armor_and_normal_player() {
        String result = formattedAttack(soldierWithWeaponAndArmor, player);

        assertThat(result, is("战士李四用优质木棒攻击了普通人张三, 张三受到了13点伤害, 张三剩余生命: -3"));
    }
}