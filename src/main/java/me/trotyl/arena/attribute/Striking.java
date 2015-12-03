package me.trotyl.arena.attribute;


import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

import java.util.Random;

public class Striking extends Attribute {

    private static Random random = new Random();
    private static float rate = 0.5f;

    public static void config(Random random, float rate) {
        Striking.random = random;
        Striking.rate = rate;
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
