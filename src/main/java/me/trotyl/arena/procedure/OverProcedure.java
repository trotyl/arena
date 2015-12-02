package me.trotyl.arena.procedure;


import me.trotyl.arena.record.PlayerRecord;

public class OverProcedure extends Procedure {

    public static final OverProcedure none = new OverProcedure(PlayerRecord.none, PlayerRecord.none);

    public PlayerRecord winner;
    public PlayerRecord loser;

    public OverProcedure(PlayerRecord winner, PlayerRecord loser) {
        this.winner = winner;
        this.loser = loser;
    }
}
