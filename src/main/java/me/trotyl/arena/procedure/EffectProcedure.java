package me.trotyl.arena.procedure;


import me.trotyl.arena.record.AttackableRecord;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.record.PlayerRecord;

public class EffectProcedure extends Procedure {

    public static final EffectProcedure none = new EffectProcedure(PlayerRecord.none, EffectRecord.none, DamageRecord.none);

    public EffectProcedure(AttackableRecord attackable, EffectRecord effect, DamageRecord damage) {
        super();
    }
}
