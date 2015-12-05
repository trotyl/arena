package me.trotyl.arena.attribute;


import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class CompositeAttribute extends Attribute {

    public static Attribute create(Attribute attribute, Attribute[] attributes) {

        if (attributes.length == 0) {
            return attribute;
        } else if (attribute.equals(Attribute.none)) {
            return CompositeAttribute.reduce(attributes);
        } else {
            Attribute[] tmp = {attribute, CompositeAttribute.reduce(attributes)};
            return CompositeAttribute.reduce(tmp);
        }
    }

    protected static Attribute reduce(Attribute[] attributes) {
        if (attributes.length == 0) {
            return Attribute.none;
        } if (attributes.length == 1) {
            return attributes[0];
        }

        List<Attribute> attributeList = stream(attributes).collect(toList());

        return new CompositeAttribute(attributeList);
    }

    private List<Attribute> attributes;

    public CompositeAttribute(List<Attribute> attributes) {
        super(-1, 1.0f);

        this.attributes = attributes;
    }

}
