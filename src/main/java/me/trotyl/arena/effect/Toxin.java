package me.trotyl.arena.effect;


import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Player;

public class Toxin extends Effect {

    public static Toxin create(int extent, int limit) {

        if (extent < 0) {
            throw new IllegalArgumentException("The extent must not be less than 0, but: " + extent);
        } else if (limit < 0) {
            throw new IllegalArgumentException("The extent must not be less than 0, but: " + limit);
        }

        return new Toxin(extent, limit);
    }

    private int extent;

    protected Toxin(int extent, int limit) {
        super(limit);

        this.extent = extent;
    }

    @Override
    public DamageRecord take(Player player) {

        DamageRecord damage = DamageRecord.create(extent, Genre.effect);

        player.suffer(damage, Effect.none);

        return damage;
    }

    protected Genre getGenre() {
        return Genre.toxic;
    }
}
