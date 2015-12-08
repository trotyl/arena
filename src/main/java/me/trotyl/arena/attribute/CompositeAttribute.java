package me.trotyl.arena.attribute;


import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Player;

public class CompositeAttribute extends AggressiveAttribute {

    private AggressiveAttribute first;
    private AggressiveAttribute second;

    public CompositeAttribute(AggressiveAttribute first, AggressiveAttribute second) {
        super(-1, 1.0f);

        this.first = first;
        this.second = second;
    }

    public AggressiveAttribute getFirst() {
        return first;
    }

    public AggressiveAttribute getSecond() {
        return second;
    }

    @Override
    public DamageRecord apply(Player attacker, Player defender, AggressiveAttribute next) {
        return first.apply(attacker, defender, second);
    }
}
