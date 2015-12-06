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

    private Attribute attribute0;
    private Attribute attribute1;

    public CompositeAttribute(Attribute attribute0, Attribute attribute1) {
        super(-1, 1.0f);

        this.attribute0 = attribute0;
        this.attribute1 = attribute1;
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable, Attribute attribute) {
        return attribute0.apply(attacker, attackable, attribute1);
    }
}
