package me.trotyl.arena;


import me.trotyl.arena.procedure.ActionProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;
import me.trotyl.arena.role.Player;
import org.javatuples.Pair;

public class Game {

    public static Game between(Player player1, Player player2) {
        return new Game(player1, player2);
    }

    protected final Player player1;
    protected final Player player2;
    protected boolean inTurnOfPlayer1;
    protected int distance;


    public Game(Player player1, Player player2) {

        this.player1 = player1;
        this.player2 = player2;
        inTurnOfPlayer1 = true;
        distance = 1;
    }

    public boolean end() {
        return !player1.alive() || !player2.alive();
    }

    public OverProcedure over() {

        if (!end()) {
            return OverProcedure.none;
        }

        Player winner = player1.alive()? player1: player2;
        Player loser = winner.equals(player1)? player2: player1;

        return OverProcedure.create(winner.record(), loser.record());
    }

    public Pair<EffectProcedure, ActionProcedure> run() {

        if (end()) {
            return new Pair<>(EffectProcedure.none, ActionProcedure.none);
        }

        Attacker attacker = inTurnOfPlayer1? player1: player2;
        Attackable attackable = attacker.equals(player1)? player2: player1;

        Pair<EffectProcedure, ActionProcedure> pair = attacker.action(attackable, distance);

        inTurnOfPlayer1 = ! inTurnOfPlayer1;

        distance -= pair.getValue1().move.decrement;
        if (distance < 1) {
            distance = 1;
        }

        int increment = pair.getValue1().attack.damage.distance;
        if (increment > 0) {
            distance += increment;
        }

        return pair;
    }
}
