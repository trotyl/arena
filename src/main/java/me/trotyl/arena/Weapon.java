package me.trotyl.arena;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.record.WeaponRecord;

public class Weapon {

    private final int aggressivity;
    private final String name;
    public Attribute attribute;

    public Weapon(String name, int aggressivity) {
        this.name = name;
        this.aggressivity = aggressivity;
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
