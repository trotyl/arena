package me.trotyl.arena.role;


import me.trotyl.arena.Armor;
import me.trotyl.arena.Weapon;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;

public class Soldier extends Player {

    private Weapon weapon;
    private Armor armor;

    public Soldier(String name, int health, int aggressivity) {
        this(name, health, aggressivity, Weapon.none, Armor.none);
    }

    public Soldier(String name, int health, int aggressivity, Weapon weapon, Armor armor) {
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
    public AttackProcedure attack(Attackable attackable) {
        DamageRecord damage = weapon.attribute.apply(this, attackable);

        return new AttackProcedure(record(), attackable.record(), damage);
    }

    @Override
    public int suffer(int injury) {
        if (injury > armor.defence()) {
            health -= (injury - armor.defence());
        }
        return injury - armor.defence();
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
        return new PlayerRecord(name, health, Role.soldier, weapon.record(), armor.record());
    }
}
