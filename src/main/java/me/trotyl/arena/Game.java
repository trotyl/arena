package me.trotyl.arena;


import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;
import me.trotyl.arena.role.Player;
import org.javatuples.Pair;

public class Game {

    protected Player player1;
    protected Player player2;
    private boolean inTurnOfPlayer1;

    public static Game between(Player player1, Player player2) {
        Game game = new Game();
        game.player1 = player1;
        game.player2 = player2;
        return game;
    }

    public Game() {
        inTurnOfPlayer1 = true;
    }

    public Pair<EffectProcedure, AttackProcedure> run() {
        if (over()) {
            return new Pair<>(EffectProcedure.none, AttackProcedure.none);
        }

        Attacker attacker = inTurnOfPlayer1? player1: player2;
        Attackable attackable = attacker.equals(player1)? player2: player1;

        Pair<EffectProcedure, AttackProcedure> pair = attacker.attack(attackable);
        inTurnOfPlayer1 = ! inTurnOfPlayer1;

        return pair;
    }

    public OverProcedure overProcedure() {
        if (!over()) {
            return OverProcedure.none;
        }

        Player winner = player1.alive()? player1: player2;
        Player loser = winner.equals(player1)? player2: player1;

        return new OverProcedure(winner.record(), loser.record());
    }

    public boolean over() {
        return !player1.alive() || !player2.alive();
    }
}
