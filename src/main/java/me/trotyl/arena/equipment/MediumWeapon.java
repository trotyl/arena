package me.trotyl.arena.equipment;

import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.Attribute;


public class MediumWeapon extends Weapon implements Defensive {

    public static MediumWeapon create(String name, int aggressivity, int defence) {
        return MediumWeapon.create(name, aggressivity, 0, Attribute.normalAttack);
    }

    public static MediumWeapon create(String name, int aggressivity, int defence, AggressiveAttribute attribute) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name must be a valid string, but: " + name);
        }

        if (attribute == null) {
            attribute = Attribute.normalAttack;
        }

        return new MediumWeapon(name, aggressivity, defence, attribute);
    }

    protected final int defence;

    protected MediumWeapon(String name, int aggressivity, int defence, AggressiveAttribute attribute) {

        super(name, aggressivity, attribute);

        this.defence = defence;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public Length getLength() {
        return Length.medium;
    }

}
