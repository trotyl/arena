package me.trotyl.arena.attribute;


import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.RepelDamageRecord;
import me.trotyl.arena.record.TrackDamageRecord;
import me.trotyl.arena.role.Player;

public class Repel extends AggressiveAttribute {

    public static Repel create(int distance) {

        if (distance < 0) {
            throw new IllegalArgumentException("The distance must be greater than 0, but: " + distance);
        }

        return new Repel(distance);
    }

    protected final int distance;

    protected Repel(int distance) {
        super(-1, 0.25f);
        this.distance = distance;
    }

    @Override
    public DamageRecord apply(Player attacker, Player defender, AggressiveAttribute next, DefensiveAttribute echo) {

        if (!works()) {
            return next.apply(attacker, defender, Attribute.normalAttack, echo);
        }

        TrackDamageRecord track = (TrackDamageRecord) next.apply(attacker, defender, Attribute.normalAttack, Attribute.noDamage);
        RepelDamageRecord repel = RepelDamageRecord.create(distance, track.damage);

        return echo.apply(repel, track.effect, attacker, defender);
    }
}
