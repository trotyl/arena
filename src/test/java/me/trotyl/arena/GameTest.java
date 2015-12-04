package me.trotyl.arena;

import me.trotyl.arena.armor.Armor;
import me.trotyl.arena.attribute.Toxic;
import me.trotyl.arena.effect.Type;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Player;
import me.trotyl.arena.role.Soldier;
import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;
import org.javatuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class GameTest {

    private Game game;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

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
    public void run_should_have_proper_result() throws Exception {

        Soldier soldier = Soldier.create("张三", 10, 5,
                new Weapon("方天画戟", 5, Length.none, Toxic.create(2, 2, 2.0f)),
                Armor.create(6));
        Player player = Player.create("李四", 20, 8);
        game = Game.between(soldier, player);

        Pair<EffectProcedure, AttackProcedure> pair;
        EffectProcedure effectProcedure;
        AttackProcedure attackProcedure;

        pair = game.run();
        effectProcedure = pair.getValue0();
        attackProcedure = pair.getValue1();

        assertThat(effectProcedure.effect, is(EffectRecord.none));
        assertThat(effectProcedure.damage, is(DamageRecord.none));
        assertThat(attackProcedure.attacker.name(), is("张三"));
        assertThat(attackProcedure.attackable.name(), is("李四"));
        assertThat(attackProcedure.attackable.health(), is(10));
        assertThat(attackProcedure.damage.extent, is(10));

        pair = game.run();
        effectProcedure = pair.getValue0();
        attackProcedure = pair.getValue1();

        assertThat(effectProcedure.effect.type, is(Type.toxin));
        assertThat(effectProcedure.damage.extent, is(2));
        assertThat(attackProcedure.attacker.name(), is("李四"));
        assertThat(attackProcedure.attackable.name(), is("张三"));
        assertThat(attackProcedure.attackable.health(), is(8));
        assertThat(attackProcedure.damage.extent, is(2));

        pair = game.run();
        effectProcedure = pair.getValue0();
        attackProcedure = pair.getValue1();

        assertThat(effectProcedure.effect, is(EffectRecord.none));
        assertThat(effectProcedure.damage, is(DamageRecord.none));
        assertThat(attackProcedure.attackable.health(), is(-2));
    }

    @Test
    public void run_should_have_proper_invocation() {

        Player player0 = mock(Player.class);
        Player player1 = mock(Player.class);
        when(player0.alive()).thenReturn(true);
        when(player1.alive()).thenReturn(true);
        when(player0.attack(player1)).thenReturn(new Pair<>(EffectProcedure.none, AttackProcedure.none));
        when(player1.attack(player0)).thenReturn(new Pair<>(EffectProcedure.none, AttackProcedure.none));

        game = Game.between(player0, player1);
        game.run();
        game.run();

        InOrder inOrder = inOrder(player0, player1);
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
        inOrder.verify(player0).attack(player1);
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
        inOrder.verify(player1).attack(player0);
        verifyNoMoreInteractions(player0, player1);
    }

    @Test
    public void over_procedure_should_have_proper_result() {

        Soldier soldier = Soldier.create("张三", 10, 5,
                new Weapon("方天画戟", 5, Length.none, Toxic.create(2, 2, 2.0f)),
                Armor.create(6));

        Player player = Player.create("李四", 20, 8);
        game = Game.between(soldier, player);

        OverProcedure procedure;

        procedure = game.overProcedure();
        assertThat(procedure, is(OverProcedure.none));

        game.run();
        procedure = game.overProcedure();
        assertThat(procedure, is(OverProcedure.none));

        game.run();
        procedure = game.overProcedure();
        assertThat(procedure, is(OverProcedure.none));

        game.run();
        procedure = game.overProcedure();
        assertThat(procedure.winner.name(), is("张三"));
        assertThat(procedure.loser.name(), is("李四"));
    }

    @Test
    public void over_procedure_should_have_proper_invocation() {

        Player player0 = mock(Player.class);
        Player player1 = mock(Player.class);
        when(player0.alive()).thenReturn(true);
        when(player1.alive()).thenReturn(false);
        when(player0.attack(player1)).thenReturn(new Pair<>(EffectProcedure.none, AttackProcedure.none));
        when(player1.attack(player0)).thenReturn(new Pair<>(EffectProcedure.none, AttackProcedure.none));

        game = Game.between(player0, player1);
        game.overProcedure();

        InOrder inOrder = inOrder(player0, player1);
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
        inOrder.verify(player0).alive();
        inOrder.verify(player0).record();
        inOrder.verify(player1).record();
        verifyNoMoreInteractions(player0, player1);
    }

    @Test
    public void over_should_have_proper_result_and_invocation() {

        Player player0 = mock(Player.class);
        Player player1 = mock(Player.class);
        when(player0.alive()).thenReturn(true);
        when(player1.alive()).thenReturn(true, true, false);

        game = Game.between(player0, player1);

        assertThat(game.over(), is(false));
        assertThat(game.over(), is(false));
        assertThat(game.over(), is(true));

        InOrder inOrder = inOrder(player0, player1);
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
        verifyNoMoreInteractions(player0, player1);
    }
}