package me.trotyl.arena.attribute;


import me.trotyl.arena.role.Player;

public class Striking extends AggressiveAttribute {

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
    protected int getDamage(Player attacker, Player defender) {

        int damage = 3 * (attacker.getAggressivity() - defender.getDefence());

        return damage > 0? damage: 0;
    }

    @Override
    protected Genre getGenre() {
        return Genre.striking;
    }
}
