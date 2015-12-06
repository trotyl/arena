package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public abstract class Attribute {

    public static Attribute none = new Attribute(-1, 0.0f) {};
    protected static Random random = new Random();

    public static Attribute create(Attribute attribute, List<Attribute> attributes) {

        Attribute composite = create(attributes);

        if (attribute.equals(Attribute.none)) {
            return composite;
        } else {
            List<Attribute> list = asList(attribute, composite);
            return create(list);
        }
    }

    public static Attribute create(List<Attribute> attributes) {

        if (attributes.size() == 0) {
            return Attribute.none;
        } else if (attributes.size() == 1) {
            return attributes.get(0);
        }

        Attribute composite = create(attributes.subList(1, attributes.size()));

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
