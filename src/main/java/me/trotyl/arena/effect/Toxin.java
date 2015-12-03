package me.trotyl.arena.effect;


import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Attackable;

public class Toxin extends Effect {

    private int extent;

    public Toxin(int extent) {
        this.extent = extent;
    }

    @Override
    public EffectRecord take(Attackable attackable) {
        attackable.suffer(extent);
        return new EffectRecord(Type.toxin, extent);
    }
}
