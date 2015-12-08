package me.trotyl.arena.attribute;


import me.trotyl.arena.record.CaromDamageRecord;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Carom extends Attribute {

    public static Carom create() {
        return new Carom();
    }

    protected Carom() {
        super(-1, 0.25f);
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable, Attribute next) {

        DamageRecord first = next.apply(attacker, attackable, Attribute.none);

        if (!works()) {
            return first;
        }

        DamageRecord second = next.apply(attacker, attackable, Attribute.none);

        return CaromDamageRecord.create(first, second);
    }
}
