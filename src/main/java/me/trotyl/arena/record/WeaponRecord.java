package me.trotyl.arena.record;


public class WeaponRecord implements Record {

    public static final WeaponRecord none = new WeaponRecord("");

    private final String name;

    public WeaponRecord(String name) {
        this.name = name.intern();
    }

    @Override
    public String name() {
        return name;
    }
}
