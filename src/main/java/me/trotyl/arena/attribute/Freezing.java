package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Freeze;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Freezing extends Attribute {

    public static Freezing create(int limit, float rate) {
        return new Freezing(limit, rate);
    }

    protected Freezing(int limit, float rate) {
        super(limit, rate);
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        return applyByEffect(attacker, attackable, new Freeze(limit), Genre.freezing);
    }
}
