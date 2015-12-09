package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.TrackDamageRecord;
import me.trotyl.arena.role.Player;

import java.util.Random;

public abstract class Attribute {

    public static AggressiveAttribute normalAttack = new AggressiveAttribute(-1, 1.0f) {

        @Override
        public boolean works() {
            return true;
        }
    };

    public static DefensiveAttribute normalDefence = new DefensiveAttribute(-1, 1.0f) {};

    public static DefensiveAttribute noDamage = new DefensiveAttribute(-1, 1.0f) {

        @Override
        public DamageRecord apply(DamageRecord damage, Effect effect, Player attacker, Player defender) {
            return TrackDamageRecord.create(damage, effect);
        }
    };

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

    protected boolean works() {
        return random.nextFloat() < rate;
    }
}
