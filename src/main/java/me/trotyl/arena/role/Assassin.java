package me.trotyl.arena.role;


import me.trotyl.arena.armor.Armor;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;

public class Assassin extends Soldier {

    public static Assassin create(String name, int health, int aggressivity) {
        return new Assassin(name, health, aggressivity, Weapon.none, Armor.none);
    }

    public static Assassin create(String name, int health, int aggressivity, Weapon weapon, Armor armor) {
        return new Assassin(name, health, aggressivity, weapon, armor);
    }

    protected Assassin(String name, int health, int aggressivity, Weapon weapon, Armor armor) {
        super(name, health, aggressivity, weapon, armor);
    }

    @Override
    public void equip(Weapon weapon) {

        if (weapon != Weapon.none && weapon.length() != Length.shorter) {
            throw new IllegalArgumentException("Assassin can only equip short weapon!");
        }

        super.equip(weapon);
    }

    @Override
    public PlayerRecord record() {
        return PlayerRecord.create(name, health, Role.assassin, weapon.record(), armor.record());
    }
}
