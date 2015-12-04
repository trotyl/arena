package me.trotyl.arena.procedure;


import me.trotyl.arena.record.PlayerRecord;

public class OverProcedure extends Procedure {

    public static final OverProcedure none = new OverProcedure(PlayerRecord.none, PlayerRecord.none);

    public static OverProcedure create(PlayerRecord winner, PlayerRecord loser) {
        return new OverProcedure(winner, loser);
    }

    public PlayerRecord winner;
    public PlayerRecord loser;

    protected OverProcedure(PlayerRecord winner, PlayerRecord loser) {
        this.winner = winner;
        this.loser = loser;
    }
}
