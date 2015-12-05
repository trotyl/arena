package me.trotyl.arena.role;


import me.trotyl.arena.armor.Armor;
import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.weapon.Length;
import me.trotyl.arena.weapon.Weapon;
import org.javatuples.Pair;

public class Knight extends Soldier {

    public static Knight create(String name, int health, int aggressivity) {
        return Knight.create(name, health, aggressivity, Weapon.none, Armor.none);
    }

    public static Knight create(String name, int health, int aggressivity, Weapon weapon, Armor armor) {

        checkParameters(name, health, aggressivity);

        if (weapon == null) {
            weapon = Weapon.none;
        } if (armor == null) {
            armor = Armor.none;
        }

        return new Knight(name, health, aggressivity, weapon, armor);
    }

    protected Knight(String name, int health, int aggressivity, Weapon weapon, Armor armor) {
        super(name, health, aggressivity, weapon, armor);
    }

    @Override
    public Pair<EffectProcedure, AttackProcedure> attack(Attackable attackable) {

        if (!weapon.length().equals(Length.longer)) {
            return attackByAttribute(attackable, Attribute.none);
        }

        return super.attack(attackable);
    }

    @Override
    public void equip(Weapon weapon) {

        if (weapon != Weapon.none &&
            weapon.length() != Length.longer &&
            weapon.length() != Length.medium) {
            throw new IllegalArgumentException("Knight can only equip long weapon!");
        }

        super.equip(weapon);
    }

    @Override
    public PlayerRecord record() {
        return PlayerRecord.create(name, health, Role.knight, weapon.record(), armor.record());
    }
}
