package me.trotyl.arena.attribute;


import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Dizzy extends Attribute {

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        return null;
    }
}
