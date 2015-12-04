package me.trotyl.arena.effect;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Flame extends Effect {

    public static Flame create(int extent, int limit) {
        return new Flame(extent, limit);
    }

    private int extent;

    protected Flame(int extent, int limit) {
        super(limit);
        this.extent = extent;
    }

    @Override
    public DamageRecord take(Attackable attackable) {
        attackable.suffer(extent, Effect.none);
        return DamageRecord.create(extent, Genre.effect);
    }

    @Override
    public EffectRecord record() {
        return EffectRecord.create(Type.flame, remain);
    }

    @Override
    public DamageRecord sway(Attacker attacker, Attackable attackable, Attribute attribute) {
        remain--;
        return super.sway(attacker, attackable, attribute);
    }
}
