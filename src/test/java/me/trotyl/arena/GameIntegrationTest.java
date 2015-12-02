package me.trotyl.arena;

import me.trotyl.arena.role.Player;
import me.trotyl.arena.role.Role;
import me.trotyl.arena.role.Soldier;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.procedure.Procedure;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class GameIntegrationTest {

    private Game game;

    @Before
    public void setUp() throws Exception {
        Soldier player1 = new Soldier("张三", 10, 8);
        Weapon weapon = new Weapon("优质木棒", 2);
        player1.equip(weapon);
        Armor armor = new Armor(5);
        player1.equip(armor);

        Player player2 = new Player("李四", 20, 9);

        game = Game.between(player1, player2);
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void should_produce_the_right_procedure() throws Exception {
        List<Procedure> procedures = game.run();

        assertThat(procedures.size(), is(4));

        Procedure procedure0 = procedures.get(0);
        assertThat(procedure0, instanceOf(AttackProcedure.class));
        AttackProcedure attackProcedure0 = (AttackProcedure)procedure0;
        assertThat(attackProcedure0.attacker.role(), is(Role.soldier));
        assertThat(attackProcedure0.attacker.name(), is("张三"));
        assertThat(attackProcedure0.attacker.weapon().name(), is("优质木棒"));
        assertThat(attackProcedure0.attackable.role(), is(Role.normal));
        assertThat(attackProcedure0.attackable.name(), is("李四"));
        assertThat(attackProcedure0.attackable.health(), is(10));
        assertThat(attackProcedure0.damage, is(10));

        Procedure procedure1 = procedures.get(1);
        assertThat(procedure1, instanceOf(AttackProcedure.class));
        AttackProcedure attackProcedure1 = (AttackProcedure)procedure1;
        assertThat(attackProcedure1.attacker.name(), is("李四"));
        assertThat(attackProcedure1.attackable.name(), is("张三"));
        assertThat(attackProcedure1.attackable.health(), is(6));
        assertThat(attackProcedure1.damage, is(4));

        Procedure procedure2 = procedures.get(2);
        assertThat(procedure2, instanceOf(AttackProcedure.class));
        AttackProcedure attackProcedure2 = (AttackProcedure)procedure2;
        assertThat(attackProcedure2.attacker.name(), is("张三"));
        assertThat(attackProcedure2.attackable.name(), is("李四"));
        assertThat(attackProcedure2.attackable.health(), is(0));
        assertThat(attackProcedure2.damage, is(10));

        Procedure procedure4 = procedures.get(3);
        assertThat(procedure4, instanceOf(OverProcedure.class));
        OverProcedure overProcedure = (OverProcedure)procedure4;
        assertThat(overProcedure.winner.name(), is("张三"));
        assertThat(overProcedure.loser.name(), is("李四"));
    }
}