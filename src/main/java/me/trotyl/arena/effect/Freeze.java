package me.trotyl.arena.effect;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Freeze extends Effect {

    private boolean frozen;

    public Freeze(int limit) {
        super(limit);

        frozen = true;
    }

    @Override
    public DamageRecord take(Attackable attackable) {
        return DamageRecord.none;
    }

    @Override
    public EffectRecord record() {
        return new EffectRecord(Type.freeze, remain);
    }

    @Override
    public DamageRecord sway(Attacker attacker, Attackable attackable, Attribute attribute) {
        remain--;
        DamageRecord damage = frozen ? DamageRecord.none : Effect.none.sway(attacker, attackable, attribute);
        frozen = !frozen;

        return damage;
    }

    @Override
    public boolean valid() {
        return remain > 0;
    }
}
