package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Swoon;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Dizzy extends Attribute {

    public static Dizzy create(float rate) {
        return new Dizzy(rate);
    }

    protected Dizzy(float rate) {
        super(2, rate);
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        return applyByEffect(attacker, attackable, Swoon.create(limit), Genre.dizzy);
    }
}
