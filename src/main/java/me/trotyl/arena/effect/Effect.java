package me.trotyl.arena.effect;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.record.Action;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public abstract class Effect {

    public static final Effect none = new Effect(0) {

        @Override
        public boolean valid() {
            return true;
        }
    };

    protected int remain;

    protected Effect(int remain) {
        this.remain = remain;
    }

    public int getRemain() {
        return remain;
    }

    public void append(int remain) {
        this.remain += remain;
    }

    public EffectRecord record(Action next) {
        return EffectRecord.create(getGenre(), getRemain(), next);
    }

    public DamageRecord sway(Attacker attacker, Attackable attackable, Attribute attribute) {
        return attribute.apply(attacker, attackable, Attribute.none);
    }

    public DamageRecord take(Attackable attackable) {
        return DamageRecord.none;
    }

    public boolean valid() {
        return remain > 0;
    }

    protected Genre getGenre() {
        return Genre.none;
    }
}
