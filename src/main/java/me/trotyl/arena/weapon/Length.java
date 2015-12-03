package me.trotyl.arena.weapon;


public class Length {

    public static final Length none = new Length("None");
    public static final Length shorter = new Length("Short");
    public static final Length medium = new Length("Medium");
    public static final Length longer = new Length("Long");

    private String name;

    public Length(String name) {
        this.name = name;
    }
}
