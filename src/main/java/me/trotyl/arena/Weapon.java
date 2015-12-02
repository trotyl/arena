package me.trotyl.arena;


public class Weapon {
    private final int aggressivity;

    public Weapon(String name, int aggressivity) {
        this.aggressivity = aggressivity;
    }

    public int aggressivity() {
        return aggressivity;
    }
}
