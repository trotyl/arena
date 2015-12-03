package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Striking extends Attribute {

    private float rate;

    public Striking(int limit, float rate) {
        super(limit, rate);
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        if (!works()) {
            return applyNoEffect(attacker, attackable);
        }

        int damage = 3 * (attacker.aggressivity() - attackable.defence());
        attackable.suffer(damage, Effect.none);

        return new DamageRecord(Genre.striking, damage);
    }
}
