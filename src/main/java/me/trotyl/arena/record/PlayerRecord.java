package me.trotyl.arena.record;


import me.trotyl.arena.role.Role;

public class PlayerRecord implements AttackerRecord, AttackableRecord {

    public static final PlayerRecord none = PlayerRecord.create("None", 0, Role.none);

    public static PlayerRecord create(String name, int health) {
        return new PlayerRecord(name, health, Role.normal, WeaponRecord.none, ArmorRecord.none);
    }

    public static PlayerRecord create(String name, int health, Role role) {
        return new PlayerRecord(name, health, role, WeaponRecord.none, ArmorRecord.none);
    }

    public static PlayerRecord create(String name, int health, Role role, WeaponRecord weapon, ArmorRecord armor) {
        return new PlayerRecord(name, health, role, weapon, armor);
    }

    private final String name;
    private final int health;
    private final Role role;
    private final WeaponRecord weapon;
    private final ArmorRecord armor;

    protected PlayerRecord(String name, int health, Role role, WeaponRecord weapon, ArmorRecord armor) {

        this.name = name.intern();
        this.health = health;
        this.role = role;
        this.weapon = weapon;
        this.armor = armor;
    }

    @Override
    public ArmorRecord getArmor() {
        return armor;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public WeaponRecord getWeapon() {
        return weapon;
    }
}
