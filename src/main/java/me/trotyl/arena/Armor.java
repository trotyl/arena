package me.trotyl.arena;


import me.trotyl.arena.status.ArmorRecord;

public class Armor {

    private final int defence;

    public Armor(int defence) {
        this.defence = defence;
    }

    public int defence() {
        return defence;
    }

    public ArmorRecord record() {
        return new ArmorRecord(defence);
    }
}
