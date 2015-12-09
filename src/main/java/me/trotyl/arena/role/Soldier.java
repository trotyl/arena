package me.trotyl.arena.role;


import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.DefensiveAttribute;
import me.trotyl.arena.equipment.Armor;
import me.trotyl.arena.equipment.Weapon;

public abstract class Soldier extends Player {

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
    public AggressiveAttribute getAggressiveAttribute() {
        return weapon.getAggressiveAttribute();
    }

    @Override
    public int getDefence() {
        return armor.getDefence();
    }

    @Override
    public DefensiveAttribute getDefensiveAttribute() {
        return weapon.getDefensiveAttribute();
    }

    @Override
    public int getRange() {
        return weapon.getRange();
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
}
