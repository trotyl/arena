package me.trotyl.arena.equipment;


import me.trotyl.arena.record.ArmorRecord;

public class Armor implements Defensive {

    public static final Armor none = new Armor(0) {

        @Override
        public ArmorRecord record() {
            return ArmorRecord.none;
        }
    };

    public static Armor create(int defence) {

        if (defence < 0) {
            throw new IllegalArgumentException("The defence must not be less than 0, but: " + defence);
        }

        return new Armor(defence);
    }

    protected final int defence;

    protected Armor(int defence) {
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }

    public ArmorRecord record() {
        return ArmorRecord.create(defence);
    }
}
