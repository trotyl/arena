package me.trotyl.arena;

import me.trotyl.arena.attribute.Toxic;
import me.trotyl.arena.effect.Type;
import me.trotyl.arena.equipment.Armor;
import me.trotyl.arena.equipment.LongWeapon;
import me.trotyl.arena.equipment.MediumWeapon;
import me.trotyl.arena.procedure.ActionProcedure;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Fighter;
import me.trotyl.arena.role.Player;
import me.trotyl.arena.role.Soldier;
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

        verifyNoMoreInteractions(player0, player1);
    }

    @Test
    public void over_should_have_proper_result() {

        Soldier soldier = Soldier.create("张三", 10, 5,
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
        when(player0.action(player1, 1)).thenReturn(new Pair<>(EffectProcedure.none, ActionProcedure.none));
        when(player1.action(player0, 1)).thenReturn(new Pair<>(EffectProcedure.none, ActionProcedure.none));

        game = Game.between(player0, player1);
        game.over();

        InOrder inOrder = inOrder(player0, player1);
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
        inOrder.verify(player0).alive();
        inOrder.verify(player0).record();
        inOrder.verify(player1).record();

        verifyNoMoreInteractions(player0, player1);
    }

    @Test
    public void run_should_have_proper_result() throws Exception {

        Soldier soldier = Soldier.create("张三", 10, 5,
                LongWeapon.create("方天画戟", 5, 0, Toxic.create(2, 2, 1.0f)),
                Armor.create(6));

        Player player = Player.create("李四", 20, 8);

        game = Game.between(soldier, player);

        Pair<EffectProcedure, ActionProcedure> pair;
        EffectProcedure effectProcedure;
        ActionProcedure actionProcedure;

        pair = game.run();
        effectProcedure = pair.getValue0();
        actionProcedure = pair.getValue1();

        assertThat(effectProcedure.effect, is(EffectRecord.none));
        assertThat(effectProcedure.damage, is(DamageRecord.none));
        assertThat(actionProcedure.attack.attacker.getName(), is("张三"));
        assertThat(actionProcedure.attack.attackable.getName(), is("李四"));
        assertThat(actionProcedure.attack.attackable.getHealth(), is(10));
        assertThat(actionProcedure.attack.damage.extent, is(10));

        pair = game.run();
        effectProcedure = pair.getValue0();
        actionProcedure = pair.getValue1();

        assertThat(effectProcedure.effect.type, is(Type.toxin));
        assertThat(effectProcedure.damage.extent, is(2));
        assertThat(actionProcedure.attack.attacker.getName(), is("李四"));
        assertThat(actionProcedure.attack.attackable.getName(), is("张三"));
        assertThat(actionProcedure.attack.attackable.getHealth(), is(8));
        assertThat(actionProcedure.attack.damage.extent, is(2));

        pair = game.run();
        effectProcedure = pair.getValue0();
        actionProcedure = pair.getValue1();

        assertThat(effectProcedure.effect, is(EffectRecord.none));
        assertThat(effectProcedure.damage, is(DamageRecord.none));
        assertThat(actionProcedure.attack.attackable.getHealth(), is(-2));
    }

    @Test
    public void run_should_have_proper_result_when_move() {

        Fighter soldier = Fighter.create("张三", 10, 5,
                MediumWeapon.create("雌雄双股剑", 5, 0),
                Armor.create(6));

        Player player = Player.create("李四", 20, 8);

        game = Game.between(soldier, player);
        game.distance = 3;

        Pair<EffectProcedure, ActionProcedure> pair;
        ActionProcedure actionProcedure;

        pair = game.run();
        actionProcedure = pair.getValue1();

        assertThat(actionProcedure.move.decrement, is(1));
        assertThat(actionProcedure.attack, is(AttackProcedure.none));

        pair = game.run();
        actionProcedure = pair.getValue1();

        assertThat(actionProcedure.move.decrement, is(1));
        assertThat(actionProcedure.attack, is(AttackProcedure.none));

        pair = game.run();
        actionProcedure = pair.getValue1();

        assertThat(actionProcedure.attack.attacker.getName(), is("张三"));
        assertThat(actionProcedure.attack.attackable.getName(), is("李四"));
    }

    @Test
    public void run_should_have_proper_invocation() {

        Player player0 = mock(Player.class);
        Player player1 = mock(Player.class);
        when(player0.alive()).thenReturn(true);
        when(player1.alive()).thenReturn(true);
        when(player0.action(player1, 1)).thenReturn(new Pair<>(EffectProcedure.none, ActionProcedure.none));
        when(player1.action(player0, 1)).thenReturn(new Pair<>(EffectProcedure.none, ActionProcedure.none));

        game = Game.between(player0, player1);
        game.run();
        game.run();

        InOrder inOrder = inOrder(player0, player1);
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
        inOrder.verify(player0).action(player1, 1);
        inOrder.verify(player0).alive();
        inOrder.verify(player1).alive();
        inOrder.verify(player1).action(player0, 1);

        verifyNoMoreInteractions(player0, player1);
    }
}
