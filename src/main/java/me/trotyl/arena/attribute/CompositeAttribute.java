package me.trotyl.arena.attribute;


import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class CompositeAttribute extends Attribute {

    public static Attribute create(Attribute attribute, Attribute[] attributes) {

        Attribute composite = CompositeAttribute.reduce(stream(attributes).collect(toList()));

        if (attribute.equals(Attribute.none)) {
            return composite;
        } else {
            List<Attribute> list = asList(attribute, composite);
            return CompositeAttribute.reduce(list);
        }
    }

    protected static Attribute reduce(List<Attribute> attributes) {

        if (attributes.size() == 0) {
            return Attribute.none;
        } else if (attributes.size() == 1) {
            return attributes.get(0);
        }

        Attribute composite = reduce(attributes.subList(1, attributes.size()));

        return new CompositeAttribute(attributes.get(0), composite);
    }

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
}
