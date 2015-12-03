package me.trotyl.arena.role;


import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;

public class Assassin extends Soldier {

    public Assassin(String name, int health, int aggressivity) {
        super(name, health, aggressivity);
    }

    @Override
    public void equip(Weapon weapon) {
        if (weapon.length() != Length.shorter) {
            throw new IllegalArgumentException("Assassin can only equip short weapon!");
        }
        super.equip(weapon);
    }
}
