package me.trotyl.arena.procedure;


import me.trotyl.arena.status.PlayerRecord;

public class OverProcedure extends Procedure {

    public PlayerRecord winner;
    public PlayerRecord loser;

    public OverProcedure(PlayerRecord winner, PlayerRecord loser) {
        this.winner = winner;
        this.loser = loser;
    }
}
