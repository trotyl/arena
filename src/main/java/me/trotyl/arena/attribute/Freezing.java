package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Freeze;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Freezing extends Attribute {

    public static Freezing create(int limit, float rate) {

        if (limit < 0) {
            throw new IllegalArgumentException("The extent must not be less than 0, but: " + limit);
        } else if (rate < 0.0f || rate > 1.0f) {
            throw new IllegalArgumentException("The rate of dizzy must be in range of 0 and 1, but: " + rate);
        }

        return new Freezing(limit, rate);
    }

    protected Freezing(int limit, float rate) {
        super(limit, rate);
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable, Attribute attribute) {
        return applyByEffect(attacker, attackable, attribute, Freeze.create(limit), Genre.freezing);
    }
}
