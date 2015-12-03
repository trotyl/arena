package me.trotyl.arena.role;


public class Role {

    public static final Role normal = new Role("Normal");
    public static final Role soldier = new Role("Soldier");
    public static final Role none = new Role("None");
    public static final Role assassin = new Role("Assassin");
    public static final Role fighter = new Role("Fighter");
    public static final Role knight = new Role("Knight");

    private String name;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
