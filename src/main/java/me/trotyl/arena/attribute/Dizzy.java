package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.effect.Swoon;

public class Dizzy extends AggressiveAttribute {

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
    protected Effect getEffect() {
        return Swoon.create(limit);
    }

    @Override
    protected Genre getGenre() {
        return Genre.dizzy;
    }
}
