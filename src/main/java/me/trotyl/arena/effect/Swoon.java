package me.trotyl.arena.effect;


import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Attackable;

public class Swoon extends Effect {
    private int limit;

    public Swoon(int limit) {
        super();

        this.limit = limit;
    }

    @Override
    public EffectRecord take(Attackable attackable) {
        return null;
    }
}
