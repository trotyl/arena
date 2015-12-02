package me.trotyl.arena.role;


public class Role {

    public static Role normal = new Role();
    public static Role soldier = new Role();

    @Override
    public String toString() {
        return this.equals(normal)? "普通人": "战士";
    }
}
