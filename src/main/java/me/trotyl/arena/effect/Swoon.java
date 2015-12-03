package me.trotyl.arena.effect;


import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Attackable;

public class Swoon extends Effect {

    public Swoon(int limit) {
        super(2);
    }

    @Override
    public EffectRecord take(Attackable attackable) {
        return null;
    }
}
