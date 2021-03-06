package me.trotyl.arena.role;


import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.equipment.*;
import me.trotyl.arena.record.PlayerRecord;

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
    public AggressiveAttribute getAggressiveAttribute() {

        if (!(weapon instanceof ShortWeapon)) {
            return Attribute.normalAttack;
        }

        return weapon.getAggressiveAttribute();
    }

    @Override
    public int getVelocity() {
        return 2;
    }

    @Override
    public void equip(Weapon weapon) {

        if (weapon != Weapon.none && !(weapon instanceof ShortWeapon) && !(weapon instanceof MediumWeapon)) {
            throw new IllegalArgumentException("Assassin can only equip short weapon!");
        }

        super.equip(weapon);
    }

    @Override
    public PlayerRecord record() {
        return PlayerRecord.create(name, health, Role.assassin, weapon.record(), armor.record());
    }
}
