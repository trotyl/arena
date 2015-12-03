package me.trotyl.arena;

import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.role.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class FormatterTest {

    private Formatter formatter;

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

        AttackProcedure attack = player1.attack(player2);
        String result = formatter.formatAttack(attack);

        assertThat(result, is("普通人张三攻击了普通人李四, 李四受到了5点伤害, 李四剩余生命: 15"));
    }
}