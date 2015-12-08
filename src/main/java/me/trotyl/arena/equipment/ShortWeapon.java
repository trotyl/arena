package me.trotyl.arena.equipment;

import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Carom;

import static java.util.Collections.singletonList;


public class ShortWeapon extends Weapon {

    public static ShortWeapon create(String name, int aggressivity) {
        return ShortWeapon.create(name, aggressivity, Attribute.none);
    }

    public static ShortWeapon create(String name, int aggressivity, Attribute attribute) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name must be a valid string, but: " + name);
        }

        if (attribute == null) {
            attribute = Attribute.none;
        }

        return new ShortWeapon(name, aggressivity, attribute);
    }

    protected ShortWeapon(String name, int aggressivity, Attribute attribute) {
        super(name, aggressivity, Attribute.compose(Carom.create(), singletonList(attribute)));
    }

    @Override
    public Length getLength() {
        return Length.shorter;
    }
}
