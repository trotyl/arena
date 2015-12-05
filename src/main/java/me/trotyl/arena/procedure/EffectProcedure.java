package me.trotyl.arena.procedure;


import me.trotyl.arena.record.AttackableRecord;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.record.PlayerRecord;

public class EffectProcedure extends Procedure {

    public static final EffectProcedure none = new EffectProcedure(PlayerRecord.none,
                                                                   PlayerRecord.none,
                                                                   EffectRecord.none,
                                                                   DamageRecord.none);

    public static EffectProcedure create(AttackableRecord host, AttackableRecord remote, EffectRecord effect, DamageRecord damage) {

        if (effect.equals(EffectRecord.none)) {
            return none;
        }

        return new EffectProcedure(host, remote, effect, damage);
    }

    public AttackableRecord host;
    public AttackableRecord remote;
    public EffectRecord effect;
    public DamageRecord damage;

    protected EffectProcedure(AttackableRecord host, AttackableRecord remote, EffectRecord effect, DamageRecord damage) {

        super();

        this.host = host;
        this.remote = remote;
        this.effect = effect;
        this.damage = damage;
    }
}
