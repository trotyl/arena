package me.trotyl.arena.attribute;

import me.trotyl.arena.effect.Toxin;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;


public class Toxic extends Attribute {

    private final int extent;

    public Toxic(int extent, int limit, float rate) {
        super(limit, rate);

        this.extent = extent;
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        return applyByEffect(attacker, attackable, new Toxin(extent, limit), Genre.toxic);
    }
}
