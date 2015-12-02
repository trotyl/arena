package me.trotyl.arena.record;


import me.trotyl.arena.role.Role;

public class PlayerRecord implements AttackerRecord, AttackableRecord {

    public static final PlayerRecord none = new PlayerRecord("", 0, Role.none);
    private final String name;
    private final int health;
    private final Role role;
    private final WeaponRecord weapon;
    private final ArmorRecord armor;

    public PlayerRecord(String name, int health, Role role) {
        this(name, health, role, WeaponRecord.none, ArmorRecord.none);
    }

    public PlayerRecord(String name, int health, Role role, WeaponRecord weapon, ArmorRecord armor) {
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
    public ArmorRecord armor() {
        return armor;
    }

    @Override
    public Role role() {
        return role;
    }

    @Override
    public WeaponRecord weapon() {
        return weapon;
    }
}
