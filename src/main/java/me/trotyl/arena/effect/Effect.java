package me.trotyl.arena.effect;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public abstract class Effect {

    public static final Effect none = new Effect(0) {

        @Override
        public DamageRecord take(Attackable attackable) {
            return DamageRecord.none;
        }

        @Override
        public EffectRecord record() {
            return EffectRecord.none;
        }

        @Override
        public DamageRecord sway(Attacker attacker, Attackable attackable, Attribute attribute) {
            return attribute.apply(attacker, attackable);
        }

        @Override
        public boolean valid() {
            return true;
        }
    };

    protected int remain;

    protected Effect(int remain) {
        this.remain = remain;
    }

    public abstract DamageRecord take(Attackable attackable);

    public abstract EffectRecord record();

    public abstract DamageRecord sway(Attacker attacker, Attackable attackable, Attribute attribute);

    public abstract boolean valid();
}
