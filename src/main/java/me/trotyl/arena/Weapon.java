package me.trotyl.arena;


import me.trotyl.arena.status.WeaponStatus;

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

    public WeaponStatus status() {
        return new WeaponStatus(name);
    }
}
