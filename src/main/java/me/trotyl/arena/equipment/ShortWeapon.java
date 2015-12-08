package me.trotyl.arena.equipment;

import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Carom;

import static java.util.Collections.singletonList;


public class ShortWeapon extends Weapon {

    public static ShortWeapon create(String name, int aggressivity) {
        return ShortWeapon.create(name, aggressivity, Attribute.normalAttack);
    }

    public static ShortWeapon create(String name, int aggressivity, AggressiveAttribute attribute) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name must be a valid string, but: " + name);
        }

        if (attribute == null) {
            attribute = Attribute.normalAttack;
        }

        return new ShortWeapon(name, aggressivity, attribute);
    }

    protected ShortWeapon(String name, int aggressivity, AggressiveAttribute attribute) {
        super(name, aggressivity, AggressiveAttribute.compose(Carom.create(), singletonList(attribute)));
    }
}
