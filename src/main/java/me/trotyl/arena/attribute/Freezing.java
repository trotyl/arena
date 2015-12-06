package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.effect.Freeze;

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
    protected Effect getEffect() {
        return Freeze.create(limit);
    }

    @Override
    protected Genre getGenre() {
        return Genre.freezing;
    }
}
