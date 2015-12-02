package me.trotyl.arena.status;


import me.trotyl.arena.role.Role;

public class PlayerStatus {

    public final String name;
    public final int health;
    public final Role role;
    public final WeaponStatus weapon;
    public final ArmorStatus armor;

    public PlayerStatus(String name, int health, Role role) {
        this(name, health, role, null, null);
    }

    public PlayerStatus(String name, int health, Role role, WeaponStatus weapon, ArmorStatus armor) {
        this.name = name.intern();
        this.health = health;
        this.role = role;
        this.weapon = weapon;
        this.armor = armor;
    }
}
