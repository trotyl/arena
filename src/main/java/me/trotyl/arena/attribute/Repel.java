package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.DamageRecord;
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

        if (!works()) {
            return super.apply(attacker, attackable, next);
        }

        int damage = getDamage(attacker, attackable);
        attackable.suffer(damage, Effect.none);

        return DamageRecord.create(damage, distance, Genre.repel);
    }
}
