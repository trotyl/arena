package me.trotyl.arena.record;


import me.trotyl.arena.attribute.Genre;

public class DamageRecord implements Record {

    public static final DamageRecord none = DamageRecord.create(0, Genre.none);

    public static DamageRecord create(int extent) {
        return new DamageRecord(extent, 0, Genre.none);
    }

    public static DamageRecord create(int extent, Genre genre) {
        return new DamageRecord(extent, 0, genre);
    }

    public static DamageRecord create(int extent, int distance, Genre genre) {
        return new DamageRecord(extent, distance, genre);
    }

    public final Genre genre;
    public int extent;
    public final int distance;

    protected DamageRecord(int extent, int distance, Genre genre) {

        this.genre = genre;
        this.distance = distance;
        this.extent = extent;
    }

    @Override
    public String getName() {
        return genre.toString()+ extent;
    }

    public void setExtent(int extent) {
        this.extent = extent;
    }
}
