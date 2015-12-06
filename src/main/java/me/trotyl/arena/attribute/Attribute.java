package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

import java.util.Random;

public abstract class Attribute {

    public static Attribute none = new Attribute(-1, 0.0f) {};
    protected static Random random = new Random();

    public static void config(Random random) {
        Attribute.random = random;
    }

    protected int limit;
    protected float rate;

    protected Attribute(int limit, float rate) {

        this.limit = limit;
        this.rate = rate;
    }

    public int getLimit() {
        return limit;
    }

    public float getRate() {
        return rate;
    }

    public DamageRecord apply(Attacker attacker, Attackable attackable, Attribute attribute) {

        int damage = attacker.getAggressivity() - attackable.getDefence();
        attackable.suffer(damage, Effect.none);

        return DamageRecord.create(damage);
    }

    protected DamageRecord applyByEffect(Attacker attacker, Attackable attackable, Attribute attribute, Effect effect, Genre genre) {

        if (!works()) {
            return attribute.apply(attacker, attackable, Attribute.none);
        }

        int damage = attacker.getAggressivity() - attackable.getDefence();
        attackable.suffer(damage, effect);

        return DamageRecord.create(damage, genre);
    }

    protected boolean works() {
        return random.nextFloat() < rate;
    }
}
