package me.trotyl.arena.record;


import me.trotyl.arena.attribute.Genre;

public class CounterDamageRecord extends DamageRecord {

    public static CounterDamageRecord create(DamageRecord original, DamageRecord counter) {
        return new CounterDamageRecord(original, counter);
    }

    public final DamageRecord original;
    public final DamageRecord counter;

    protected CounterDamageRecord(DamageRecord original, DamageRecord counter) {

        super(0, 0, Genre.counter);

        this.original = original;
        this.counter = counter;
    }
}
