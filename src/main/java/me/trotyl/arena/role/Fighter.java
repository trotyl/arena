package me.trotyl.arena.role;


import me.trotyl.arena.armor.Armor;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;

public class Fighter extends Soldier {

    public static Fighter create(String name, int health, int aggressivity) {
        return new Fighter(name, health, aggressivity, Weapon.none, Armor.none);
    }

    public static Fighter create(String name, int health, int aggressivity, Weapon weapon, Armor armor) {
        return new Fighter(name, health, aggressivity, weapon, armor);
    }

    protected Fighter(String name, int health, int aggressivity, Weapon weapon, Armor armor) {
        super(name, health, aggressivity, weapon, armor);
    }

    @Override
    public void equip(Weapon weapon) {
        if (weapon != Weapon.none && weapon.length() != Length.medium) {
            throw new IllegalArgumentException("Fighter can only equip medium weapon!");
        }
        super.equip(weapon);
    }

    @Override
    public PlayerRecord record() {
        return PlayerRecord.create(name, health, Role.fighter, weapon.record(), armor.record());
    }
}
