package me.trotyl.arena.attribute;


import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public interface Attribute {
    public DamageRecord apply(Attacker attacker, Attackable attackable);
}
