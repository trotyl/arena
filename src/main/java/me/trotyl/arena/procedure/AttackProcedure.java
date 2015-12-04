package me.trotyl.arena.procedure;


import me.trotyl.arena.record.AttackableRecord;
import me.trotyl.arena.record.AttackerRecord;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;

public class AttackProcedure extends Procedure {

    public static final AttackProcedure none = new AttackProcedure(
            PlayerRecord.none,
            PlayerRecord.none,
            DamageRecord.none);

    public static AttackProcedure create(AttackerRecord attacker, AttackableRecord attackable, DamageRecord damage) {

        if (damage.equals(DamageRecord.none)) {
            return none;
        }

        return new AttackProcedure(attacker, attackable, damage);
    }

    public AttackerRecord attacker;
    public AttackableRecord attackable;
    public DamageRecord damage;

    protected AttackProcedure(AttackerRecord attacker, AttackableRecord attackable, DamageRecord damage) {

        super();

        this.attacker = attacker;
        this.attackable = attackable;
        this.damage = damage;
    }
}
