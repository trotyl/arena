package me.trotyl.arena.attribute;


import me.trotyl.arena.record.CaromDamageRecord;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Player;

public class Carom extends AggressiveAttribute {

    public static Carom create() {
        return new Carom();
    }

    protected Carom() {
        super(-1, 0.25f);
    }

    @Override
    public DamageRecord apply(Player attacker, Player defender, AggressiveAttribute next) {

        DamageRecord first = next.apply(attacker, defender, Attribute.normalAttack);

        if (!works()) {
            return first;
        }

        DamageRecord second = next.apply(attacker, defender, Attribute.normalAttack);

        return CaromDamageRecord.create(first, second);
    }
}
