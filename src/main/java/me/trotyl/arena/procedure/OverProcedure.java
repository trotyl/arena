package me.trotyl.arena.procedure;


import me.trotyl.arena.status.PlayerStatus;

public class OverProcedure extends Procedure {
    public PlayerStatus winner;
    public PlayerStatus loser;

    public OverProcedure(PlayerStatus winner, PlayerStatus loser) {
        this.winner = winner;
        this.loser = loser;
    }
}
