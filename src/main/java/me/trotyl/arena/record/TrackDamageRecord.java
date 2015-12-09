package me.trotyl.arena.record;


import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.effect.Effect;

public class TrackDamageRecord extends DamageRecord {

    public static TrackDamageRecord create(DamageRecord damage, Effect effect) {
        return new TrackDamageRecord(damage, effect);
    }

    public final DamageRecord damage;
    public final Effect effect;

    protected TrackDamageRecord(DamageRecord damage, Effect effect) {

        super(0, 0, Genre.track);

        this.damage = damage;
        this.effect = effect;
    }
}
