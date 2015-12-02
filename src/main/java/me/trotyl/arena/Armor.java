package me.trotyl.arena;


import me.trotyl.arena.status.ArmorStatus;

public class Armor {

    private final int defence;

    public Armor(int defence) {
        this.defence = defence;
    }

    public int defence() {
        return defence;
    }

    public ArmorStatus status() {
        return new ArmorStatus(defence);
    }
}
