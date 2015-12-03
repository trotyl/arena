package me.trotyl.arena.attribute;


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
            int damage = attacker.aggressivity() - attackable.defence();
            attackable.suffer(damage);

            return new DamageRecord(damage);
        }
    };

    protected int limit;
    protected float rate;

    public Attribute(int limit, float rate) {
        this.limit = limit;
        this.rate = rate;
    }

    public abstract DamageRecord apply(Attacker attacker, Attackable attackable);
}
