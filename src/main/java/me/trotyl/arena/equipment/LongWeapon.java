package me.trotyl.arena.equipment;

import me.trotyl.arena.attribute.Attribute;


public class LongWeapon extends Weapon {

    public static LongWeapon create(String name, int aggressivity, int repel) {
        return LongWeapon.create(name, aggressivity, repel, Attribute.none);
    }

    public static LongWeapon create(String name, int aggressivity, int repel, Attribute attribute) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name must be a valid string, but: " + name);
        } if (repel < 0 || repel > 2) {
            throw new IllegalArgumentException("The reple must be in range of 0 and 2, but: " + repel);
        }

        if (attribute == null) {
            attribute = Attribute.none;
        }

        return new LongWeapon(name, aggressivity, repel, attribute);
    }

    protected final int repel;

    protected LongWeapon(String name, int aggressivity, int repel, Attribute attributes) {

        super(name, aggressivity, attributes);

        this.repel = repel;
    }

    @Override
    public Attribute getAttribute() {
        return attribute;
    }

    @Override
    public Length getLength() {
        return Length.longer;
    }
}
