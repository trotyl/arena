package me.trotyl.arena.equipment;

import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Repel;

import static java.util.Collections.singletonList;


public class LongWeapon extends Weapon {

    public static LongWeapon create(String name, int aggressivity, int repel) {
        return LongWeapon.create(name, aggressivity, repel, Attribute.normalAttack);
    }

    public static LongWeapon create(String name, int aggressivity, int repel, AggressiveAttribute attribute) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name must be a valid string, but: " + name);
        } if (repel < 0 || repel > 2) {
            throw new IllegalArgumentException("The reple must be in range of 0 and 2, but: " + repel);
        }

        if (attribute == null) {
            attribute = Attribute.normalAttack;
        }

        return new LongWeapon(name, aggressivity, repel, attribute);
    }

    protected final int repel;

    protected LongWeapon(String name, int aggressivity, int repel, AggressiveAttribute attribute) {

        super(name, aggressivity, AggressiveAttribute.compose(Repel.create(repel), singletonList(attribute)));

        this.repel = repel;
    }

    @Override
    public AggressiveAttribute getAttribute() {
        return attribute;
    }
}
