package me.trotyl.arena.role;


public class Role {

    public static final Role normal = new Role();
    public static final Role soldier = new Role();
    public static final Role none = new Role();

    @Override
    public String toString() {
        return this.equals(normal)? "普通人": "战士";
    }
}
