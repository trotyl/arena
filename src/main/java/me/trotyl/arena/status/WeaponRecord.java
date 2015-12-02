package me.trotyl.arena.status;


public class WeaponRecord implements Record {

    private final String name;

    public WeaponRecord(String name) {
        this.name = name.intern();
    }

    @Override
    public String name() {
        return name;
    }
}
