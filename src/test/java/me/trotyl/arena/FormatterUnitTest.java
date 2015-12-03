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

    private String formattedAttack(Attacker attacker, Attackable attackable) {
        AttackProcedure attack = attacker.attack(attackable);
        return formatter.formatAttack(attack);
    }

    @Before
    public void setUp() throws Exception {
        formatter = new Formatter();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_work_of_attack_between_normal_players() {
        Player player1 = new Player("张三", 10, 5);
        Player player2 = new Player("李四", 20, 8);

        String result = formattedAttack(player1, player2);

        assertThat(result, is("普通人张三攻击了普通人李四, 李四受到了5点伤害, 李四剩余生命: 15"));
    }

    @Test
    public void should_work_of_attack_between_normal_player_and_soldier_without_equipment() {
        Player player1 = new Player("张三", 10, 5);
        Soldier player2 = new Soldier("李四", 20, 8);

        String result = formattedAttack(player1, player2);

        assertThat(result, is("普通人张三攻击了战士李四, 李四受到了5点伤害, 李四剩余生命: 15"));
    }

    @Test
    public void should_work_of_attack_between_normal_player_and_soldier_with_weapon_but_no_armor() {
        Player player1 = new Player("张三", 10, 5);
        Soldier player2 = new Soldier("李四", 20, 8);
        player2.equip(new Weapon("优质木棒", 5));

        String result = formattedAttack(player1, player2);

        assertThat(result, is("普通人张三攻击了战士李四, 李四受到了5点伤害, 李四剩余生命: 15"));
    }

    @Test
    public void should_work_of_attack_between_normal_player_and_soldier_with_armor_but_no_weapon() {
        Player player1 = new Player("张三", 10, 5);
        Soldier player2 = new Soldier("李四", 20, 8);
        player2.equip(new Armor(3));

        String result = formattedAttack(player1, player2);

        assertThat(result, is("普通人张三攻击了战士李四, 李四受到了2点伤害, 李四剩余生命: 18"));
    }

    @Test
    public void should_work_of_attack_between_normal_player_and_soldier_with_weapon_and_armor() {
        Player player1 = new Player("张三", 10, 5);
        Soldier player2 = new Soldier("李四", 20, 8);
        player2.equip(new Weapon("优质木棒", 5));
        player2.equip(new Armor(3));

        String result = formattedAttack(player1, player2);

        assertThat(result, is("普通人张三攻击了战士李四, 李四受到了2点伤害, 李四剩余生命: 18"));
    }

    @Test
    public void should_work_of_attack_between_soldier_without_equipment_and_normal_player() {
        Player player1 = new Player("张三", 10, 5);
        Soldier player2 = new Soldier("李四", 20, 8);

        String result = formattedAttack(player2, player1);

        assertThat(result, is("战士李四攻击了普通人张三, 张三受到了8点伤害, 张三剩余生命: 2"));
    }
}