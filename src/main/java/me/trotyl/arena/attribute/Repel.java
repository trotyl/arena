package me.trotyl.arena.attribute;


import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.RepelDamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Repel extends Attribute {

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
    public DamageRecord apply(Attacker attacker, Attackable attackable, Attribute next) {

        DamageRecord record = next.apply(attacker, attackable, Attribute.none);

        if (!works()) {
            return record;
        }

        return RepelDamageRecord.create(distance, record);
    }
}
