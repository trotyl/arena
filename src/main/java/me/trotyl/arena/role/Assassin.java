package me.trotyl.arena.role;


import me.trotyl.arena.equipment.Armor;
import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.equipment.Length;
import me.trotyl.arena.equipment.Weapon;

public class Assassin extends Soldier {

    public static Assassin create(String name, int health, int aggressivity) {
        return Assassin.create(name, health, aggressivity, Weapon.none, Armor.none);
    }

    public static Assassin create(String name, int health, int aggressivity, Weapon weapon, Armor armor) {

        checkParameters(name, health, aggressivity);

        if (weapon == null) {
            weapon = Weapon.none;
        } if (armor == null) {
            armor = Armor.none;
        }

        return new Assassin(name, health, aggressivity, weapon, armor);
    }

    protected Assassin(String name, int health, int aggressivity, Weapon weapon, Armor armor) {
        super(name, health, aggressivity, weapon, armor);
    }

    @Override
    public Attribute getAttribute() {

        if (!weapon.getLength().equals(Length.shorter)) {
            return Attribute.none;
        }

        return weapon.getAttribute();
    }

    @Override
    public void equip(Weapon weapon) {

        if (weapon != Weapon.none &&
            weapon.getLength() != Length.shorter &&
            weapon.getLength() != Length.medium) {
            throw new IllegalArgumentException("Assassin can only equip short weapon!");
        }

        super.equip(weapon);
    }

    @Override
    protected int getVelocity() {
        return 2;
    }

    @Override
    public PlayerRecord record() {
        return PlayerRecord.create(name, health, Role.assassin, weapon.record(), armor.record());
    }
}
