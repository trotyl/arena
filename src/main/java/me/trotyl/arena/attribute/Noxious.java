package me.trotyl.arena.attribute;

import me.trotyl.arena.effect.Toxin;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

import java.util.Random;


public class Noxious extends Attribute {

    private static Random random = new Random();

    public static void config(Random random) {
        Noxious.random = random;
    }

    private float rate = 0.5f;
    private final int extent;

    public Noxious(int extent, float rate) {
        super();

        this.extent = extent;
        this.rate = rate;
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        if (random.nextFloat() > rate) {
            return Attribute.none.apply(attacker, attackable);
        }

        Toxin toxin = new Toxin(extent);
        int damage = attacker.aggressivity() - attackable.defence();
        attackable.suffer(damage, toxin);
        return null;
    }
}
