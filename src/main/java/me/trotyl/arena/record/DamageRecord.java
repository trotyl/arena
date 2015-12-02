package me.trotyl.arena.record;


import me.trotyl.arena.attribute.Genre;

public class DamageRecord implements Record {

    public static final DamageRecord none = new DamageRecord(Genre.none, 0);

    public final Genre genre;
    public final int damage;

    public DamageRecord(int damage) {
        this(Genre.none, damage);
    }

    public DamageRecord(Genre genre, int damage) {
        this.genre = genre;
        this.damage = damage;
    }

    @Override
    public String name() {
        return genre.toString();
    }
}
