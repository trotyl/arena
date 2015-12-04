package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Striking extends Attribute {

    public static Striking create(float rate) {
        return new Striking(rate);
    }

    protected Striking(float rate) {
        super(-1, rate);
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        if (!works()) {
            return super.apply(attacker, attackable);
        }

        int damage = 3 * (attacker.aggressivity() - attackable.defence());
        attackable.suffer(damage, Effect.none);

        return DamageRecord.create(damage, Genre.striking);
    }
}
