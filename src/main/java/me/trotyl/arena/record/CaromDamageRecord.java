package me.trotyl.arena.record;

import me.trotyl.arena.attribute.Genre;


public class CaromDamageRecord extends DamageRecord {

    public static CaromDamageRecord create(int damage, int another) {
        return new CaromDamageRecord(damage, another);
    }

    public final int another;

    protected CaromDamageRecord(int extent, int another) {
        super(extent, 0, Genre.carom);

        this.another = another;
    }
}
