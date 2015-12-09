package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.CounterDamageRecord;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Player;

public class Counter extends DefensiveAttribute {

    public static Counter create(int defence) {
        return new Counter(defence);
    }

    protected final int defence;

    protected Counter(int defence) {

        super(-1, 0.25f);

        this.defence = defence;
    }

    @Override
    public DamageRecord apply(DamageRecord damage, Effect effect, Player attacker, Player defender) {

        if (!works()) {
            return super.apply(damage, effect, attacker, defender);
        }

        int extent = damage.extent - defence;

        if (extent < 0) {
            extent = 0;
        }

        DamageRecord newDamage = DamageRecord.create(extent, damage.distance, damage.genre);
        DamageRecord original = super.apply(newDamage, effect, attacker, defender);

        if (!defender.alive()) {
            return original;
        }


        if (defender.getRange() < defender.getGame().getDistance()) {
            return CounterDamageRecord.create(original, DamageRecord.none);
        }

        AggressiveAttribute aggressive = defender.getAggressiveAttribute();
        DamageRecord counter = aggressive.apply(defender, attacker, Attribute.normalAttack, Attribute.normalDefence);

        return CounterDamageRecord.create(original, counter);
    }
}
