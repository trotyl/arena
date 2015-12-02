package me.trotyl.arena.procedure;


import me.trotyl.arena.record.AttackableRecord;
import me.trotyl.arena.record.AttackerRecord;

public class AttackProcedure extends Procedure {

    public AttackerRecord attacker;
    public AttackableRecord attackable;
    public int damage;

    public AttackProcedure(AttackerRecord attacker, AttackableRecord attackable, int damage) {
        super();

        this.attacker = attacker;
        this.attackable = attackable;
        this.damage = damage;
    }
}
