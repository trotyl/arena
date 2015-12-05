package me.trotyl.arena.record;


public class WeaponRecord implements Record {

    public static final WeaponRecord none = new WeaponRecord("None");

    public static WeaponRecord create(String name) {
        return new WeaponRecord(name);
    }

    private final String name;

    protected WeaponRecord(String name) {
        this.name = name.intern();
    }

    @Override
    public String getName() {
        return name;
    }
}
