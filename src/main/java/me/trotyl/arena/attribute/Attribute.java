package me.trotyl.arena.attribute;


import java.util.Random;

public abstract class Attribute {

    public static AggressiveAttribute normalAttack = new AggressiveAttribute(-1, 0.0f) {

        @Override
        public boolean works() {
            return true;
        }
    };

    public static DefensiveAttribute normalDefence = new DefensiveAttribute(-1, 0.0f) {};

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
