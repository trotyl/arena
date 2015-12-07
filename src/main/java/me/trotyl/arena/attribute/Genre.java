package me.trotyl.arena.attribute;


public class Genre {

    public static final Genre none = new Genre("None");

    public static final Genre striking = new Genre("Striking");
    public static final Genre effect = new Genre("Effect");
    public static final Genre toxic = new Genre("Toxic");
    public static final Genre dizzy = new Genre("Dizzy");
    public static final Genre flaming = new Genre("Flaming");
    public static final Genre freezing = new Genre("Freezing");
    public static final Genre repel = new Genre("Repel");

    private String name;

    public Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
