package me.trotyl.arena.procedure;


import me.trotyl.arena.status.PlayerStatus;

public class AttackProcedure extends Procedure {
    public PlayerStatus attacker;
    public PlayerStatus defender;
    public int damage;

    public AttackProcedure(PlayerStatus attacker, PlayerStatus defender, int damage) {
        super();

        this.attacker = attacker;
        this.defender = defender;
        this.damage = damage;
    }
}
