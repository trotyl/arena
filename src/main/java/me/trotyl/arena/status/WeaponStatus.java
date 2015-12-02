package me.trotyl.arena.status;


public class WeaponStatus implements Status {

    private final String name;

    public WeaponStatus(String name) {
        this.name = name.intern();
    }

    @Override
    public String name() {
        return name;
    }
}
