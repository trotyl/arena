package me.trotyl.arena.effect;


import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Player;

public class Swoon extends Effect {

    public static Swoon create(int limit) {

        if (limit < 0) {
            throw new IllegalArgumentException("The extent must not be less than 0, but: " + limit);
        }

        return new Swoon(limit);
    }

    protected Swoon(int limit) {
        super(limit);
    }

    @Override
    public int rein(Player player) {
        return 0;
    }

    @Override
    public DamageRecord sway(Player attacker, Player defender, AggressiveAttribute attribute) {
        return DamageRecord.none;
    }

    protected Genre getGenre() {
        return Genre.dizzy;
    }
}
