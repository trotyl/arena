package me.trotyl.arena.role;


import me.trotyl.arena.armor.Armor;
import me.trotyl.arena.weapon.Weapon;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.PlayerRecord;
import org.javatuples.Pair;

public class Soldier extends Player {

    public static Soldier create(String name, int health, int aggressivity) {
        return new Soldier(name, health, aggressivity, Weapon.none, Armor.none);
    }

    public static Soldier create(String name, int health, int aggressivity, Weapon weapon, Armor armor) {
        return new Soldier(name, health, aggressivity, weapon, armor);
    }

    protected Weapon weapon;
    protected Armor armor;

    protected Soldier(String name, int health, int aggressivity, Weapon weapon, Armor armor) {

        super(name, health, aggressivity);

        this.weapon = weapon;
        this.armor = armor;
    }

    public void equip(Weapon weapon) {
        this.weapon = weapon;
    }

    public void equip(Armor armor) {
        this.armor = armor;
    }

    @Override
    public Pair<EffectProcedure, AttackProcedure> attack(Attackable attackable) {
        return attackByAttribute(attackable, weapon.attribute());
    }

    @Override
    public int aggressivity() {
        return aggressivity + weapon.aggressivity();
    }

    @Override
    public int defence() {
        return armor.defence();
    }

    @Override
    public PlayerRecord record() {
        return PlayerRecord.create(name, health, Role.soldier, weapon.record(), armor.record());
    }

    public Weapon weapon() {
        return weapon;
    }

    public Armor armor() {
        return armor;
    }
}
