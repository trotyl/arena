package me.trotyl.arena.attribute;


import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

import java.util.Random;

public class Strike extends Attribute {

    private final Random random;
    private final float rate;

    public Strike(Random random, float rate) {
        this.random = random;
        this.rate = rate;
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        if (random.nextFloat() > rate) {
            return null;
        }

        int damage = 3 * (attacker.aggressivity() - attackable.defence());
        attackable.suffer(damage);

        return new DamageRecord(Genre.strike, damage);
    }
}
