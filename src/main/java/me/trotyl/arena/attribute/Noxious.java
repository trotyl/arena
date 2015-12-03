package me.trotyl.arena.attribute;

import me.trotyl.arena.effect.Toxin;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

import java.util.Random;


public class Noxious extends Attribute {

    private static Random random = new Random();
    private static float rate = 0.5f;
    private final int extent;

    public static void config(Random random, float rate) {
        Noxious.random = random;
        Noxious.rate = rate;
    }

    public Noxious(int extent) {
        super();

        this.extent = extent;
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
