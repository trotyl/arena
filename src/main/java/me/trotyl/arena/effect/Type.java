package me.trotyl.arena.effect;


public class Type {

    public static final Type none = new Type("None");

    public static final Type toxin = new Type("Toxin");
    public static final Type swoon = new Type("Swoon");
    public static final Type flame = new Type("Flame");
    public static final Type freeze = new Type("Freeze");

    private String name;

    public Type(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
