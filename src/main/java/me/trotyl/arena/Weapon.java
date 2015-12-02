package me.trotyl.arena;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.record.WeaponRecord;

public class Weapon {

    public static final Weapon none = new Weapon("", 0) {
        @Override
        public WeaponRecord record() {
            return WeaponRecord.none;
        }
    };

    private final int aggressivity;
    private final String name;
    public Attribute attribute;

    public Weapon(String name, int aggressivity) {
        this(name, aggressivity, Attribute.none);
    }

    public Weapon(String name, int aggressivity, Attribute attribute) {
        this.name = name;
        this.aggressivity = aggressivity;
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
}
