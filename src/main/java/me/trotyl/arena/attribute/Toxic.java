package me.trotyl.arena.attribute;

import me.trotyl.arena.effect.Toxin;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;


public class Toxic extends Attribute {

    public static Toxic create(int extent, int limit, float rate) {
        return new Toxic(extent, limit, rate);
    }

    protected final int extent;

    protected Toxic(int extent, int limit, float rate) {

        super(limit, rate);

        this.extent = extent;
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        return applyByEffect(attacker, attackable, new Toxin(extent, limit), Genre.toxic);
    }

    public int extent() {
        return extent;
    }
}
