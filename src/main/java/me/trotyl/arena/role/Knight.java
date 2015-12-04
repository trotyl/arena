package me.trotyl.arena.role;


import me.trotyl.arena.armor.Armor;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;

public class Knight extends Soldier {

    public static Knight create(String name, int health, int aggressivity) {
        return new Knight(name, health, aggressivity, Weapon.none, Armor.none);
    }

    public static Knight create(String name, int health, int aggressivity, Weapon weapon, Armor armor) {
        return new Knight(name, health, aggressivity, weapon, armor);
    }

    protected Knight(String name, int health, int aggressivity, Weapon weapon, Armor armor) {
        super(name, health, aggressivity, weapon, armor);
    }

    @Override
    public void equip(Weapon weapon) {
        if (weapon != Weapon.none && weapon.length() != Length.longer) {
            throw new IllegalArgumentException("Knight can only equip long weapon!");
        }
        super.equip(weapon);
    }

    @Override
    public PlayerRecord record() {
        return PlayerRecord.create(name, health, Role.knight, weapon.record(), armor.record());
    }
}
