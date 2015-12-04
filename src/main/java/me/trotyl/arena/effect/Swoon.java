package me.trotyl.arena.effect;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Swoon extends Effect {

    public static Swoon create(int limit) {
        return new Swoon(limit);
    }

    protected Swoon(int limit) {
        super(limit);
    }

    @Override
    public DamageRecord take(Attackable attackable) {
        return DamageRecord.none;
    }

    @Override
    public EffectRecord record() {
        return new EffectRecord(Type.swoon, remain);
    }

    @Override
    public DamageRecord sway(Attacker attacker, Attackable attackable, Attribute attribute) {

        remain--;
        return DamageRecord.none;
    }

    @Override
    public boolean valid() {
        return remain > 0;
    }
}
