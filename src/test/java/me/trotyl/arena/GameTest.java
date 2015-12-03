package me.trotyl.arena;

import me.trotyl.arena.attribute.Toxic;
import me.trotyl.arena.effect.Type;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Player;
import me.trotyl.arena.role.Soldier;
import org.javatuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;


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
        Soldier soldier = new Soldier("张三", 10, 5,
                new Weapon("方天画戟", 5, new Toxic(2, 2, 2.0f)),
                new Armor(6));
        Player player = new Player("李四", 20, 8);
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
}