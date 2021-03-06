package me.trotyl.arena;

import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.attribute.Toxic;
import me.trotyl.arena.equipment.Armor;
import me.trotyl.arena.equipment.LongWeapon;
import me.trotyl.arena.equipment.MediumWeapon;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.MoveProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.role.Fighter;
import me.trotyl.arena.role.Knight;
import me.trotyl.arena.role.Player;
import org.javatuples.Triplet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class GameTest {

    private Game game;
    Random random;

    @Before
    public void setUp() throws Exception {

        random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.5f);

        Attribute.config(random);
    }

    @After
    public void tearDown() throws Exception {
        Attribute.config(new Random());
    }

    @Test
    public void between_should_have_proper_result(){

        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);

        Game game = Game.between(player1, player2);

        assertThat(game.player1, is(player1));
        assertThat(game.player2, is(player2));
    }

    @Test
    public void end_should_have_proper_result() {

        Player player0 = mock(Player.class);
        Player player1 = mock(Player.class);
        when(player0.alive()).thenReturn(true);
        when(player1.alive()).thenReturn(true, true, false);

        game = Game.between(player0, player1);

        assertThat(game.end(), is(false));
        assertThat(game.end(), is(false));
        assertThat(game.end(), is(true));
    }

    @Test
    public void end_should_have_proper_invocation() {

        Player player0 = mock(Player.class);
        Player player1 = mock(Player.class);
        when(player0.alive()).thenReturn(true);
        when(player1.alive()).thenReturn(true, true, false);

        game = Game.between(player0, player1);

        game.end();
        game.end();
        game.end();

        InOrder inOrder = inOrder(player0, player1);
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
    }

    @Test
    public void over_should_have_proper_result() {

        Knight soldier = Knight.create("张三", 10, 5,
                LongWeapon.create("方天画戟", 5, 0, Toxic.create(2, 2, 1.0f)),
                Armor.create(6));

        Player player = Player.create("李四", 20, 8);
        game = Game.between(soldier, player);

        OverProcedure procedure;

        procedure = game.over();
        assertThat(procedure, is(OverProcedure.none));

        game.run();
        procedure = game.over();
        assertThat(procedure, is(OverProcedure.none));

        game.run();
        procedure = game.over();
        assertThat(procedure, is(OverProcedure.none));

        game.run();
        procedure = game.over();
        assertThat(procedure.winner.getName(), is("张三"));
        assertThat(procedure.loser.getName(), is("李四"));
    }

    @Test
    public void over_should_have_proper_invocation() {

        Player player0 = mock(Player.class);
        Player player1 = mock(Player.class);
        when(player0.alive()).thenReturn(true);
        when(player1.alive()).thenReturn(false);
        when(player0.action(player1))
                .thenReturn(new Triplet<>(EffectProcedure.none, MoveProcedure.none, AttackProcedure.none));
        when(player1.action(player0))
                .thenReturn(new Triplet<>(EffectProcedure.none, MoveProcedure.none, AttackProcedure.none));

        game = Game.between(player0, player1);
        game.over();

        InOrder inOrder = inOrder(player0, player1);
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
        inOrder.verify(player0).alive();
        inOrder.verify(player0).record();
        inOrder.verify(player1).record();
    }

    @Test
    public void run_should_have_proper_result() throws Exception {

        Knight soldier = Knight.create("张三", 10, 5,
                LongWeapon.create("方天画戟", 5, 0, Toxic.create(2, 2, 1.0f)),
                Armor.create(6));

        Player player = Player.create("李四", 20, 8);

        game = Game.between(soldier, player);

        Triplet<EffectProcedure, MoveProcedure, AttackProcedure> triplet;
        EffectProcedure effectProcedure;
        MoveProcedure moveProcedure;
        AttackProcedure attackProcedure;

        triplet = game.run();
        effectProcedure = triplet.getValue0();
        moveProcedure = triplet.getValue1();
        attackProcedure = triplet.getValue2();

        assertThat(effectProcedure, is(EffectProcedure.none));
        assertThat(moveProcedure, is(MoveProcedure.none));
        assertThat(attackProcedure.attacker.getName(), is("张三"));
        assertThat(attackProcedure.defender.getName(), is("李四"));
        assertThat(attackProcedure.defender.getHealth(), is(10));
        assertThat(attackProcedure.damage.extent, is(10));

        triplet = game.run();
        effectProcedure = triplet.getValue0();
        moveProcedure = triplet.getValue1();
        attackProcedure = triplet.getValue2();

        assertThat(effectProcedure.effect.genre, is(Genre.toxic));
        assertThat(effectProcedure.damage.extent, is(2));
        assertThat(moveProcedure, is(MoveProcedure.none));
        assertThat(attackProcedure.attacker.getName(), is("李四"));
        assertThat(attackProcedure.defender.getName(), is("张三"));
        assertThat(attackProcedure.defender.getHealth(), is(8));
        assertThat(attackProcedure.damage.extent, is(2));

        triplet = game.run();
        effectProcedure = triplet.getValue0();
        moveProcedure = triplet.getValue1();
        attackProcedure = triplet.getValue2();

        assertThat(effectProcedure, is(EffectProcedure.none));
        assertThat(moveProcedure, is(MoveProcedure.none));
        assertThat(attackProcedure.defender.getHealth(), is(-2));
    }

    @Test
    public void run_should_have_proper_result_when_move() {

        Fighter soldier = Fighter.create("张三", 10, 5,
                MediumWeapon.create("雌雄双股剑", 5, 0),
                Armor.create(6));

        Player player = Player.create("李四", 20, 8);

        game = Game.between(soldier, player);
        game.distance = 3;

        Triplet<EffectProcedure, MoveProcedure, AttackProcedure> triplet;
        AttackProcedure attackProcedure;

        triplet = game.run();
        attackProcedure = triplet.getValue2();

        assertThat(attackProcedure, is(AttackProcedure.none));
        assertThat(game.distance, is(2));

        triplet = game.run();
        attackProcedure = triplet.getValue2();

        assertThat(attackProcedure, is(AttackProcedure.none));
        assertThat(game.distance, is(1));

        triplet = game.run();
        attackProcedure = triplet.getValue2();

        assertThat(attackProcedure.attacker.getName(), is("张三"));
        assertThat(attackProcedure.defender.getName(), is("李四"));
    }

    @Test
    public void run_should_have_proper_result_when_repel() {

        random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.0f);

        Attribute.config(random);

        Knight knight = Knight.create("张三", 10, 5,
                LongWeapon.create("丈八蛇矛", 5, 1),
                Armor.create(6));

        Player player = Player.create("李四", 20, 8);

        game = Game.between(knight, player);

        Triplet<EffectProcedure, MoveProcedure, AttackProcedure> triplet;
        MoveProcedure moveProcedure;

        triplet = game.run();
        moveProcedure = triplet.getValue1();

        assertThat(moveProcedure, is(MoveProcedure.none));
        assertThat(game.distance, is(2));
    }

    @Test
    public void run_should_have_proper_invocation() {

        Player player0 = mock(Player.class);
        Player player1 = mock(Player.class);
        when(player0.alive()).thenReturn(true);
        when(player1.alive()).thenReturn(true);
        when(player0.action(player1))
                .thenReturn(new Triplet<>(EffectProcedure.none, MoveProcedure.none, AttackProcedure.none));
        when(player1.action(player0))
                .thenReturn(new Triplet<>(EffectProcedure.none, MoveProcedure.none, AttackProcedure.none));

        game = Game.between(player0, player1);
        game.run();
        game.run();

        InOrder inOrder = inOrder(player0, player1);
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
        inOrder.verify(player0).action(player1);
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
        inOrder.verify(player1).action(player0);
    }
}
