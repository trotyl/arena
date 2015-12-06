package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Swoon;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Dizzy extends Attribute {

    public static Dizzy create(float rate) {

        if (rate < 0.0f || rate > 1.0f) {
            throw new IllegalArgumentException("The rate must be in range of 0 and 1, but: " + rate);
        }

        return new Dizzy(rate);
    }

    protected Dizzy(float rate) {
        super(2, rate);
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable, Attribute attribute) {
        return applyByEffect(attacker, attackable, attribute, Swoon.create(limit), Genre.dizzy);
    }
}
