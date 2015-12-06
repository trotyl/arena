package me.trotyl.arena.role;


import me.trotyl.arena.armor.Armor;
import me.trotyl.arena.weapon.Weapon;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.PlayerRecord;
import org.javatuples.Pair;

public class Soldier extends Player {

    public static Soldier create(String name, int health, int aggressivity) {
        return Soldier.create(name, health, aggressivity, Weapon.none, Armor.none);
    }

    public static Soldier create(String name, int health, int aggressivity, Weapon weapon, Armor armor) {

        checkParameters(name, health, aggressivity);

        if (weapon == null) {
            weapon = Weapon.none;
        } if (armor == null) {
            armor = Armor.none;
        }

        return new Soldier(name, health, aggressivity, weapon, armor);
    }

    protected Weapon weapon;
    protected Armor armor;

    protected Soldier(String name, int health, int aggressivity, Weapon weapon, Armor armor) {

        super(name, health, aggressivity);

        this.equip(weapon);
        this.equip(armor);
    }

    @Override
    public int getAggressivity() {
        return aggressivity + weapon.getAggressivity();
    }

    public Armor getArmor() {
        return armor;
    }

    @Override
    public int getDefence() {
        return armor.getDefence();
    }

    public Weapon getWeapon() {
        return weapon;
    }


    public void equip(Armor armor) {
        this.armor = armor;
    }

    public void equip(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public Pair<EffectProcedure, AttackProcedure> attack(Attackable attackable) {
        return attackByAttribute(attackable, weapon.getAttribute());
    }

    @Override
    public PlayerRecord record() {
        return PlayerRecord.create(name, health, Role.soldier, weapon.record(), armor.record());
    }

}
