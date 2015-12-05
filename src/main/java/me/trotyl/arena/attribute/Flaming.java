package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Flame;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Flaming extends Attribute {

    public static Flaming create(int extent, int limit, float rate) {

        if (extent < 0) {
            throw new IllegalArgumentException("The extent must not be less than 0, but: " + extent);
        } else if (limit < 0) {
            throw new IllegalArgumentException("The extent must not be less than 0, but: " + limit);
        } else if (rate < 0.0f || rate > 1.0f) {
            throw new IllegalArgumentException("The rate of dizzy must be in range of 0 and 1, but: " + rate);
        }

        return new Flaming(extent, limit, rate);
    }

    protected int extent;

    protected Flaming(int extent, int limit, float rate) {

        super(limit, rate);

        this.extent = extent;
    }

    public int getExtent() {
        return extent;
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        return applyByEffect(attacker, attackable, Flame.create(extent, limit), Genre.flaming);
    }
}
