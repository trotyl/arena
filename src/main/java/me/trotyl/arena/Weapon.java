package me.trotyl.arena;


import me.trotyl.arena.record.WeaponRecord;

public class Weapon {

    private final int aggressivity;
    private final String name;

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
}
