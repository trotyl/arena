package me.trotyl.arena.effect;


import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Attackable;

public abstract class Effect {
    public static final Effect none = new Effect() {

        @Override
        public EffectRecord take(Attackable attackable) {
            return EffectRecord.none;
        }
    };

    public abstract EffectRecord take(Attackable attackable);
}
