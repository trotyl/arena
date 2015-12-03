package me.trotyl.arena.effect;


import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.record.EffectRecord;

public class Toxin extends Effect {

    private int extent;

    public Toxin(int extent) {
        this.extent = extent;
    }

    @Override
    public EffectRecord take() {
        return new EffectRecord(Type.toxin, extent);

    }
}
