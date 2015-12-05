package me.trotyl.arena.effect;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Toxin extends Effect {

    public static Toxin create(int extent, int limit) {

        if (extent < 0) {
            throw new IllegalArgumentException("The extent must not be less than 0, but: " + extent);
        } else if (limit < 0) {
            throw new IllegalArgumentException("The extent must not be less than 0, but: " + limit);
        }

        return new Toxin(extent, limit);
    }

    private int extent;

    protected Toxin(int extent, int limit) {
        super(limit);

        this.extent = extent;
    }

    @Override
    public EffectRecord record() {
        return EffectRecord.create(Type.toxin, remain);
    }

    @Override
    public DamageRecord sway(Attacker attacker, Attackable attackable, Attribute attribute) {

        remain--;

        return super.sway(attacker, attackable, attribute);
    }

    @Override
    public DamageRecord take(Attackable attackable) {

        attackable.suffer(extent, Effect.none);

        return DamageRecord.create(extent, Genre.effect);
    }
}
