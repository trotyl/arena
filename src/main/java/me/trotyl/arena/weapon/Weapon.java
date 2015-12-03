package me.trotyl.arena.weapon;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.record.WeaponRecord;

public class Weapon {

    public static final Weapon none = new Weapon("", 0, Length.none) {
        @Override
        public WeaponRecord record() {
            return WeaponRecord.none;
        }
    };

    private final int aggressivity;
    private final String name;
    private Attribute attribute;
    private Length length;

    public Weapon(String name, int aggressivity, Length length) {
        this(name, aggressivity, length, Attribute.none);
    }

    public Weapon(String name, int aggressivity, Length length, Attribute attribute) {
        this.name = name;
        this.aggressivity = aggressivity;
        this.length = length;
        this.attribute = attribute;
    }

    public int aggressivity() {
        return aggressivity;
    }

    public String name() {
        return name;
    }

    public WeaponRecord record() {
        return new WeaponRecord(name);
    }

    public void with(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute attribute() {
        return attribute;
    }

    public Length length() {
        return length;
    }
}
