package me.trotyl.arena.attribute;


import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Striking extends Attribute {

    public static Striking create(float rate) {

        if (rate < 0.0f || rate > 1.0f) {
            throw new IllegalArgumentException("The rate of dizzy must be in range of 0 and 1, but: " + rate);
        }

        return new Striking(rate);
    }

    protected Striking(float rate) {
        super(-1, rate);
    }

    @Override
    protected int getDamage(Attacker attacker, Attackable attackable) {
        return 3 * (attacker.getAggressivity() - attackable.getDefence());
    }

    @Override
    protected Genre getGenre() {
        return Genre.striking;
    }
}
