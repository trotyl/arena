package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public abstract class Attribute {

    public static Attribute none = new Attribute(-1, 0.0f) {

        @Override
        public boolean works() {
            return true;
        }
    };
    protected static Random random = new Random();

    public static Attribute compose(Attribute attribute, List<Attribute> attributes) {

        Attribute composite = compose(attributes);

        if (attribute.equals(Attribute.none)) {
            return composite;
        } else if (composite.equals(Attribute.none)) {
            return attribute;
        } else {
            List<Attribute> list = asList(attribute, composite);
            return compose(list);
        }
    }

    public static Attribute compose(List<Attribute> attributes) {

        if (attributes.size() == 0) {
            return Attribute.none;
        } else if (attributes.size() == 1) {
            return attributes.get(0);
        }

        Attribute composite = compose(attributes.subList(1, attributes.size()));

        return new CompositeAttribute(attributes.get(0), composite);
    }

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

    public DamageRecord apply(Attacker attacker, Attackable attackable, Attribute next) {

        if (!works()) {
            return next.apply(attacker, attackable, Attribute.none);
        }

        int damage = getDamage(attacker, attackable);
        attackable.suffer(damage, getEffect());

        return DamageRecord.create(damage, getGenre());
    }

    protected int getDamage(Attacker attacker, Attackable attackable) {
        return attacker.getAggressivity() - attackable.getDefence();
    }

    protected Effect getEffect() {
        return Effect.none;
    }

    protected Genre getGenre() {
        return Genre.none;
    }

    protected boolean works() {
        return random.nextFloat() < rate;
    }
}
