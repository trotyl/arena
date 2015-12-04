package me.trotyl.arena.record;


import me.trotyl.arena.attribute.Genre;

public class DamageRecord implements Record {

    public static final DamageRecord none = DamageRecord.create(0, Genre.none);

    public static DamageRecord create(int extent) {
        return new DamageRecord(extent, Genre.none);
    }

    public static DamageRecord create(int extent, Genre genre) {
        return new DamageRecord(extent, genre);
    }

    public final Genre genre;
    public final int extent;

    protected DamageRecord(int extent, Genre genre) {

        this.genre = genre;
        this.extent = extent;
    }

    @Override
    public String name() {
        return genre.toString();
    }
}
