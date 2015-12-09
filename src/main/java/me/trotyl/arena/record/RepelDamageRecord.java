package me.trotyl.arena.record;


import me.trotyl.arena.attribute.Genre;

public class RepelDamageRecord extends DamageRecord {

    public static RepelDamageRecord create(int distance, DamageRecord inner) {
        return new RepelDamageRecord(distance, inner);
    }

    public final DamageRecord inner;

    protected RepelDamageRecord(int distance, DamageRecord inner) {

        super(inner.extent, distance, Genre.repel);

        this.inner = inner;
    }

    @Override
    public void setExtent(int extent) {

        this.extent = extent;
        this.inner.extent = extent;
    }
}
