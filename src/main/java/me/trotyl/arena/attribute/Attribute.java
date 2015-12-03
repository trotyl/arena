package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

import java.util.Random;

public abstract class Attribute {

    protected static Random random = new Random();

    public static void config(Random random) {
        Attribute.random = random;
    }

    public static Attribute none = new Attribute(-1, 0.0f) {
        @Override
        public DamageRecord apply(Attacker attacker, Attackable attackable) {
            return applyNoEffect(attacker, attackable);
        }
    };

    protected int limit;
    protected float rate;

    public Attribute(int limit, float rate) {
        this.limit = limit;
        this.rate = rate;
    }

    public abstract DamageRecord apply(Attacker attacker, Attackable attackable);

    protected boolean works() {
        return random.nextFloat() < rate;
    }

    protected DamageRecord applyByEffect(Attacker attacker, Attackable attackable, Effect effect, Genre genre) {
        if (!works()) {
            return applyNoEffect(attacker, attackable);
        }

        int damage = attacker.aggressivity() - attackable.defence();
        attackable.suffer(damage, effect);

        return new DamageRecord(genre, damage);
    }

    protected DamageRecord applyNoEffect(Attacker attacker, Attackable attackable) {
        int damage = attacker.aggressivity() - attackable.defence();
        attackable.suffer(damage, Effect.none);

        return new DamageRecord(damage);
    }

    public float rate() {
        return rate;
    }

    public int limit() {
        return limit;
    }
}
