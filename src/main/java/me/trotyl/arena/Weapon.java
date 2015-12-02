package me.trotyl.arena;


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
}
