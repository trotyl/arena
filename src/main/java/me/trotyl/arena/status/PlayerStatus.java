package me.trotyl.arena.status;


import me.trotyl.arena.role.Role;

public class PlayerStatus implements AttackerStatus, AttackableStatus {

    private final String name;
    private final int health;
    private final Role role;
    private final WeaponStatus weapon;
    private final ArmorStatus armor;

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

    @Override
    public String name() {
        return name;
    }

    @Override
    public int health() {
        return health;
    }

    @Override
    public ArmorStatus armor() {
        return armor;
    }

    @Override
    public Role role() {
        return role;
    }

    @Override
    public WeaponStatus weapon() {
        return weapon;
    }
}
