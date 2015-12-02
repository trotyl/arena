package me.trotyl.arena.status;


public class WeaponStatus {

    public final int aggressivity;
    public final String name;

    public WeaponStatus(String name, int aggressivity) {
        this.name = name.intern();
        this.aggressivity = aggressivity;
    }
}
