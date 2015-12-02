package me.trotyl.arena;

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
    private Player player1;
    private Player player2;

    @Before
    public void setUp() throws Exception {
        player1 = new Player("张三", 10, 8);
        player2 = new Player("李四", 20, 9);

        game = new Game();
        game.setPlayer1(player1);
        game.setPlayer2(player2);
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void should_produce_the_right_procedure() throws Exception {
        List<Procedure> procedures = game.run();

        assertThat(procedures.size(), is(5));

        Procedure procedure0 = procedures.get(0);
        assertThat(procedure0, instanceOf(AttackProcedure.class));
        AttackProcedure attackProcedure0 = (AttackProcedure)procedure0;
        assertThat(attackProcedure0.attacker.name(), is("张三"));
        assertThat(attackProcedure0.attacker.health(), is(10));
        assertThat(attackProcedure0.defender.name(), is("李四"));
        assertThat(attackProcedure0.defender.health(), is(12));
        assertThat(attackProcedure0.damage, is(8));

        Procedure procedure1 = procedures.get(1);
        assertThat(procedure1, instanceOf(AttackProcedure.class));
        AttackProcedure attackProcedure1 = (AttackProcedure)procedure1;
        assertThat(attackProcedure1.attacker.name(), is("李四"));
        assertThat(attackProcedure1.attacker.health(), is(12));
        assertThat(attackProcedure1.defender.name(), is("张三"));
        assertThat(attackProcedure1.defender.health(), is(1));
        assertThat(attackProcedure1.damage, is(9));

        Procedure procedure2 = procedures.get(2);
        assertThat(procedure2, instanceOf(AttackProcedure.class));
        AttackProcedure attackProcedure2 = (AttackProcedure)procedure2;
        assertThat(attackProcedure2.attacker.name(), is("张三"));
        assertThat(attackProcedure2.attacker.health(), is(1));
        assertThat(attackProcedure2.defender.name(), is("李四"));
        assertThat(attackProcedure2.defender.health(), is(4));
        assertThat(attackProcedure2.damage, is(8));

        Procedure procedure3 = procedures.get(3);
        assertThat(procedure3, instanceOf(AttackProcedure.class));
        AttackProcedure attackProcedure3 = (AttackProcedure)procedure3;
        assertThat(attackProcedure3.attacker.name(), is("李四"));
        assertThat(attackProcedure3.attacker.health(), is(4));
        assertThat(attackProcedure3.defender.name(), is("张三"));
        assertThat(attackProcedure3.defender.health(), is(-8));
        assertThat(attackProcedure3.damage, is(9));

        Procedure procedure4 = procedures.get(4);
        assertThat(procedure4, instanceOf(OverProcedure.class));
        OverProcedure overProcedure = (OverProcedure)procedure4;
        assertThat(overProcedure.winner.name(), is("李四"));
        assertThat(overProcedure.loser.name(), is("张三"));
    }
}