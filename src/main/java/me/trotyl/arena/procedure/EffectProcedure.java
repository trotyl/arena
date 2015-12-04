package me.trotyl.arena.procedure;


import me.trotyl.arena.record.AttackableRecord;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.record.PlayerRecord;

public class EffectProcedure extends Procedure {

    public static final EffectProcedure none = new EffectProcedure(PlayerRecord.none,
                                                                   EffectRecord.none,
                                                                   DamageRecord.none);

    public static EffectProcedure create(AttackableRecord attackable, EffectRecord effect, DamageRecord damage) {
        return new EffectProcedure(attackable, effect, damage);
    }

    public AttackableRecord attackable;
    public EffectRecord effect;
    public DamageRecord damage;

    protected EffectProcedure(AttackableRecord attackable, EffectRecord effect, DamageRecord damage) {

        super();

        this.attackable = attackable;
        this.effect = effect;
        this.damage = damage;
    }
}
