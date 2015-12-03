package me.trotyl.arena.role;


import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;

public class Knight extends Soldier {

    public Knight(String name, int health, int aggressivity) {
        super(name, health, aggressivity);
    }

    @Override
    public void equip(Weapon weapon) {
        if (weapon.length() != Length.longer) {
            throw new IllegalArgumentException("Knight can only equip long weapon!");
        }
        super.equip(weapon);
    }

    @Override
    public PlayerRecord record() {
        return new PlayerRecord(name, health, Role.knight, weapon.record(), armor.record());
    }
}
