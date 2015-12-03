package me.trotyl.arena.effect;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Toxin extends Effect {

    private int extent;

    public Toxin(int extent, int limit) {
        super(limit);

        this.extent = extent;
    }

    @Override
    public DamageRecord take(Attackable attackable) {
        attackable.suffer(extent);
        return new DamageRecord(Genre.effect, extent);
    }

    @Override
    public EffectRecord record() {
        return new EffectRecord(Type.toxin, remain);
    }

    @Override
    public DamageRecord sway(Attacker attacker, Attackable attackable, Attribute attribute) {
        remain--;
        return Effect.none.sway(attacker, attackable, attribute);
    }

    @Override
    public boolean valid() {
        return remain > 0;
    }
}
