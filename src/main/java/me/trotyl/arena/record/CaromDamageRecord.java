package me.trotyl.arena.record;

import me.trotyl.arena.attribute.Genre;


public class CaromDamageRecord extends DamageRecord {

    public static CaromDamageRecord create(DamageRecord first, DamageRecord second) {
        return new CaromDamageRecord(first, second);
    }

    public final DamageRecord first;
    public final DamageRecord second;

    protected CaromDamageRecord(DamageRecord first, DamageRecord second) {

        super(0, 0, Genre.carom);

        this.first = first;
        this.second = second;
    }
}
