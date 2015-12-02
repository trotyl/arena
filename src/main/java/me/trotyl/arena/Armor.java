package me.trotyl.arena;


import me.trotyl.arena.record.ArmorRecord;

public class Armor {

    public static final Armor none = new Armor(0) {
        @Override
        public ArmorRecord record() {
            return ArmorRecord.none;
        }
    };

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
