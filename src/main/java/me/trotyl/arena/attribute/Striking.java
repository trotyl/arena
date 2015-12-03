package me.trotyl.arena.attribute;


import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

import java.util.Random;

public class Striking extends Attribute {

    private static Random random = new Random();

    public static void config(Random random) {
        Striking.random = random;
    }

    private float rate;

    public Striking(float rate) {
        this.rate = rate;
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        if (random.nextFloat() > rate) {
            return Attribute.none.apply(attacker, attackable);
        }

        int damage = 3 * (attacker.aggressivity() - attackable.defence());
        attackable.suffer(damage);

        return new DamageRecord(Genre.striking, damage);
    }
}
