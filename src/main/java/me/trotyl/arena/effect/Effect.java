package me.trotyl.arena.effect;


import me.trotyl.arena.record.EffectRecord;

public abstract class Effect {
    public static final Effect none = new Effect() {

        @Override
        public EffectRecord take() {
            return EffectRecord.none;
        }
    };

    public abstract EffectRecord take();
}
