package me.trotyl.arena.attribute;

import me.trotyl.arena.effect.Toxin;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;


public class Noxious extends Attribute {

    private final int extent;

    public Noxious(int extent, int limit, float rate) {
        super(limit, rate);

        this.extent = extent;
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        if (random.nextFloat() > rate) {
            return Attribute.none.apply(attacker, attackable);
        }

        Toxin toxin = new Toxin(extent, limit);
        int damage = attacker.aggressivity() - attackable.defence();
        attackable.suffer(damage, toxin);

        return new DamageRecord(Genre.noxious, damage);
    }
}
