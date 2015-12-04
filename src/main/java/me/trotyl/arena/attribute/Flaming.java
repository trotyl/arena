package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Flame;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Flaming extends Attribute {

    public static Flaming create(int extent, int limit, float rate) {
        return new Flaming(extent, limit, rate);
    }

    protected int extent;

    protected Flaming(int extent, int limit, float rate) {

        super(limit, rate);
        this.extent = extent;
    }

    public int extent() {
        return extent;
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        return applyByEffect(attacker, attackable, Flame.create(extent, limit), Genre.flaming);
    }
}
