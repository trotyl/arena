package me.trotyl.arena.procedure;


import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;

public class AttackProcedure extends Procedure {

    public static final AttackProcedure none = new AttackProcedure(PlayerRecord.none,
                                                                   PlayerRecord.none,
                                                                   DamageRecord.none);

    public static AttackProcedure create(PlayerRecord attacker, PlayerRecord defender, DamageRecord damage) {

        if (damage.equals(DamageRecord.none)) {
            return none;
        }

        return new AttackProcedure(attacker, defender, damage);
    }

    public PlayerRecord attacker;
    public PlayerRecord defender;
    public DamageRecord damage;

    protected AttackProcedure(PlayerRecord attacker, PlayerRecord defender, DamageRecord damage) {

        super();

        this.attacker = attacker;
        this.defender = defender;
        this.damage = damage;
    }
}
