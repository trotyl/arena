package me.trotyl.arena.role;


import me.trotyl.arena.equipment.*;
import me.trotyl.arena.record.PlayerRecord;

public class Fighter extends Soldier {

    public static Fighter create(String name, int health, int aggressivity) {
        return Fighter.create(name, health, aggressivity, Weapon.none, Armor.none);
    }

    public static Fighter create(String name, int health, int aggressivity, Weapon weapon, Armor armor) {

        checkParameters(name, health, aggressivity);

        if (weapon == null) {
            weapon = Weapon.none;
        } if (armor == null) {
            armor = Armor.none;
        }

        return new Fighter(name, health, aggressivity, weapon, armor);
    }

    protected Fighter(String name, int health, int aggressivity, Weapon weapon, Armor armor) {
        super(name, health, aggressivity, weapon, armor);
    }

    @Override
    public void equip(Weapon weapon) {

        if (weapon != Weapon.none && !(weapon instanceof MediumWeapon)) {
            throw new IllegalArgumentException("Fighter can only equip medium weapon!");
        }

        super.equip(weapon);
    }

    @Override
    public int getDefence() {
        return weapon.getDefence() + armor.getDefence();
    }

    @Override
    public PlayerRecord record() {
        return PlayerRecord.create(name, health, Role.fighter, weapon.record(), armor.record());
    }
}
