package me.trotyl.arena.role;


import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;

public class Fighter extends Soldier {

    public Fighter(String name, int health, int aggressivity) {
        super(name, health, aggressivity);
    }

    @Override
    public void equip(Weapon weapon) {
        if (weapon.length() != Length.medium) {
            throw new IllegalArgumentException("Fighter can only equip medium weapon!");
        }
        super.equip(weapon);
    }
}
