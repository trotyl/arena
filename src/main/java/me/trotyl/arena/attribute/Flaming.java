package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Fire;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Flaming extends Attribute {

    private int extent;

    public Flaming(int extent, int limit, float rate) {
        super(limit, rate);
        this.extent = extent;
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        return applyByEffect(attacker, attackable, new Fire(extent, limit), Genre.flaming);
    }
}