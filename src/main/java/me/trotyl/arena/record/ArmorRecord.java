package me.trotyl.arena.record;


public class ArmorRecord implements Record {

    public static final ArmorRecord none = new ArmorRecord(0);

    public static ArmorRecord create(int defence) {
        return new ArmorRecord(defence);
    }

    public final int defence;

    protected ArmorRecord(int defence) {
        this.defence = defence;
    }

    @Override
    public String name() {
        return "ArmorRecord";
    }
}
