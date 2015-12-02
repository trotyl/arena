package me.trotyl.arena.status;


public class ArmorStatus implements Status {

    public final int defence;

    public ArmorStatus(int defence) {
        this.defence = defence;
    }

    @Override
    public String name() {
        return null;
    }
}
