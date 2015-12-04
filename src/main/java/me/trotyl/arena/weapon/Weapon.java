package me.trotyl.arena.weapon;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.record.WeaponRecord;

public class Weapon {

    public static final Weapon none = Weapon.create("None", 0, Length.none);

    public static Weapon create(String name, int aggressivity, Length length) {
        return new Weapon(name, aggressivity, length, Attribute.none);
    }

    public static Weapon create(String name, int aggressivity, Length length, Attribute attribute) {
        return new Weapon(name, aggressivity, length, attribute);
    }

    private final int aggressivity;
    private final String name;
    private Attribute attribute;
    private Length length;

    protected Weapon(String name, int aggressivity, Length length, Attribute attribute) {

        this.name = name;
        this.aggressivity = aggressivity;
        this.length = length;
        this.attribute = attribute;
    }

    public int aggressivity() {
        return aggressivity;
    }

    public Attribute attribute() {
        return attribute;
    }

    public Length length() {
        return length;
    }

    public String name() {
        return name;
    }

    public WeaponRecord record() {

        if (this.equals(none)) {
            return WeaponRecord.none;
        }

        return WeaponRecord.create(name);
    }
}
