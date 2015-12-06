package me.trotyl.arena.attribute;


import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

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
    public DamageRecord apply(Attacker attacker, Attackable attackable, Attribute attribute) {
        return first.apply(attacker, attackable, second);
    }

    @Override
    protected boolean works() {
        return true;
    }
}
