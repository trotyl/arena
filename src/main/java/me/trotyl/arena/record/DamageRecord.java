package me.trotyl.arena.record;


import me.trotyl.arena.attribute.Genre;

public class DamageRecord implements Record {

    public static final DamageRecord none = new DamageRecord(Genre.none, 0);

    public final Genre genre;
    public final int extent;

    public DamageRecord(int extent) {
        this(Genre.none, extent);
    }

    public DamageRecord(Genre genre, int extent) {
        this.genre = genre;
        this.extent = extent;
    }

    @Override
    public String name() {
        return genre.toString();
    }
}
