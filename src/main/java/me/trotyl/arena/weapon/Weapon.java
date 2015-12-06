package me.trotyl.arena.weapon;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.record.WeaponRecord;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class Weapon {

    public static final Weapon none = Weapon.create("None", 0, Length.none);

    public static Weapon create(String name, int aggressivity, Length length) {
        return Weapon.create(name, aggressivity, length, Attribute.none);
    }

    public static Weapon create(String name, int aggressivity, Length length, Attribute attribute) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name must be a valid string, but: " + name);
        }

        if (length == null) {
            length = Length.none;
        }

        if (attribute == null) {
            attribute = Attribute.none;
        }

        return new Weapon(name, aggressivity, length, attribute);
    }

    private final int aggressivity;
    private final String name;
    protected Attribute attribute;
    private Length length;

    protected Weapon(String name, int aggressivity, Length length, Attribute attributes) {

        this.name = name;
        this.aggressivity = aggressivity;
        this.length = length;
        this.attribute = attributes;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public int getAggressivity() {
        return aggressivity;
    }

    public Length getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public WeaponRecord record() {

        if (this.equals(none)) {
            return WeaponRecord.none;
        }

        return WeaponRecord.create(name);
    }

    public void raise(Attribute... attributes) {
        this.attribute = Attribute.create(this.attribute, stream(attributes).collect(toList()));
    }
}
