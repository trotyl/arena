package me.trotyl.arena.attribute;


import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Player;

public class CompositeAttribute extends Attribute {

    private Attribute first;
    private Attribute second;

    public CompositeAttribute(Attribute first, Attribute second) {
        super(-1, 1.0f);

        this.first = first;
        this.second = second;
    }

    public Attribute getFirst() {
        return first;
    }

    public Attribute getSecond() {
        return second;
    }

    @Override
    public DamageRecord apply(Player attacker, Player defender, Attribute next) {
        return first.apply(attacker, defender, second);
    }
}
