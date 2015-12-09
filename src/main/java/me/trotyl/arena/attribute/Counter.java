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
    private  boolean works;

    protected Counter(int defence) {

        super(-1, 0.25f);

        this.defence = defence;
        works = works();
    }

    @Override
    public DamageRecord apply(DamageRecord damage, Effect effect, Player attacker, Player defender) {

        boolean currentWorks = works;
        works = works();

        if (!currentWorks) {
            return super.apply(damage, effect, attacker, defender);
        }

        DamageRecord original = super.apply(damage, effect, attacker, defender);

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

    @Override
    public int getDefence() {
        return works? defence: 0;
    }
}
