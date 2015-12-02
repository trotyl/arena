package me.trotyl.arena.procedure;


import me.trotyl.arena.record.AttackableRecord;
import me.trotyl.arena.record.AttackerRecord;
import me.trotyl.arena.record.DamageRecord;

public class AttackProcedure extends Procedure {

    public AttackerRecord attacker;
    public AttackableRecord attackable;
    public DamageRecord damage;

    public AttackProcedure(AttackerRecord attacker, AttackableRecord attackable, DamageRecord damage) {
        super();

        this.attacker = attacker;
        this.attackable = attackable;
        this.damage = damage;
    }
}
