package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Player;

public abstract class DefensiveAttribute extends Attribute {

    protected DefensiveAttribute(int limit, float rate) {
        super(limit, rate);
    }

    public DamageRecord apply(DamageRecord damage, Effect effect, Player attacker, Player defender) {

        defender.suffer(damage, effect);

        return damage;
    }
}
