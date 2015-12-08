package me.trotyl.arena.attribute;


import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Player;

public class DefensiveAttribute extends Attribute {

    protected DefensiveAttribute(int limit, float rate) {
        super(limit, rate);
    }

    public void apply(DamageRecord damage, Player attacker, Player defender) { }
}
