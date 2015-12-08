package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.CounterDamageRecord;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Player;

public class Counter extends DefensiveAttribute {

    protected Counter() {
        super(-1, 0.25f);
    }

    public DamageRecord apply(DamageRecord damage, Effect effect, Player attacker, Player defender) {

        DamageRecord original = super.apply(damage, effect, attacker, defender);

        if (!defender.alive()) {
            return original;
        }

        AggressiveAttribute aggressive = defender.getAggressiveAttribute();
        DamageRecord counter = aggressive.apply(defender, attacker, Attribute.normalAttack, Attribute.normalDefence);

        return CounterDamageRecord.create(original, counter);
    }
}
