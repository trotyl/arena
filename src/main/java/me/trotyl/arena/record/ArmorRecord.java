package me.trotyl.arena.record;


public class ArmorRecord implements Record {

    public final int defence;

    public ArmorRecord(int defence) {
        this.defence = defence;
    }

    @Override
    public String name() {
        return null;
    }
}
