package me.trotyl.arena.procedure;


import me.trotyl.arena.status.AttackableStatus;
import me.trotyl.arena.status.AttackerStatus;

public class AttackProcedure extends Procedure {

    public AttackerStatus attacker;
    public AttackableStatus defender;
    public int damage;

    public AttackProcedure(AttackerStatus attacker, AttackableStatus defender, int damage) {
        super();

        this.attacker = attacker;
        this.defender = defender;
        this.damage = damage;
    }
}
