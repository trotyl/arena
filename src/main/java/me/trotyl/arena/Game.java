package me.trotyl.arena;


import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;
import me.trotyl.arena.role.Player;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.procedure.Procedure;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Player player1;
    private Player player2;
    private boolean inTurnOfPlayer1;

    public static Game between(Player player1, Player player2) {
        Game game = new Game();
        game.setPlayer1(player1);
        game.setPlayer2(player2);

        return game;
    }

    public Game() {
        inTurnOfPlayer1 = true;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public List<Procedure> run() {
        List<Procedure> procedures = new ArrayList<Procedure>();

        while (!over()) {
            procedures.add(runStep());
        }

        try {
            procedures.add(overProcedure());
        } catch (Exception ignored) {}

        return procedures;
    }

    public AttackProcedure runStep() {
        Attacker attacker = inTurnOfPlayer1? player1: player2;
        Attackable attackable = attacker.equals(player1)? player2: player1;

        AttackProcedure procedure = attacker.attack(attackable);
        inTurnOfPlayer1 = ! inTurnOfPlayer1;

        return procedure;
    }

    public OverProcedure overProcedure() throws Exception {
        if (!over()) {
            throw new Exception("The game is not over yet.");
        }

        Player winner = player1.alive()? player1: player2;
        Player loser = winner.equals(player1)? player2: player1;

        return new OverProcedure(winner.record(), loser.record());
    }

    public boolean over() {
        return !player1.alive() || !player2.alive();
    }
}
