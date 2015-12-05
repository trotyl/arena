package me.trotyl.arena.effect;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Swoon extends Effect {

    public static Swoon create(int limit) {

        if (limit < 0) {
            throw new IllegalArgumentException("The extent must not be less than 0, but: " + limit);
        }

        return new Swoon(limit);
    }

    protected Swoon(int limit) {
        super(limit);
    }

    @Override
    public EffectRecord record() {
        return EffectRecord.create(Type.swoon, remain);
    }

    @Override
    public DamageRecord sway(Attacker attacker, Attackable attackable, Attribute attribute) {

        remain--;

        return DamageRecord.none;
    }
}
