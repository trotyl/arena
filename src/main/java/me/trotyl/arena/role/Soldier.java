package me.trotyl.arena.role;


import me.trotyl.arena.Armor;
import me.trotyl.arena.Weapon;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.record.ArmorRecord;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.record.WeaponRecord;

public class Soldier extends Player {

    private Weapon weapon;
    private Armor armor;

    public Soldier(String name, int health, int aggressivity) {
        super(name, health, aggressivity);
    }

    public void equip(Weapon weapon) {
        this.weapon = weapon;
    }

    public void equip(Armor armor) {
        this.armor = armor;
    }

    @Override
    public AttackProcedure attack(Attackable attackable) {
        DamageRecord damage;

        if (weapon != null && weapon.attribute != null) {
            damage = weapon.attribute.apply(this, attackable);
        } else {
            damage = new DamageRecord(null, attackable.suffer(aggressivity()));
        }

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
        return aggressivity + (weapon != null? weapon.aggressivity(): 0);
    }

    @Override
    public int defence() {
        return armor != null? armor.defence(): 0;
    }

    @Override
    public PlayerRecord record() {
        WeaponRecord weaponRecord = weapon != null? weapon.record(): null;
        ArmorRecord armorRecord = armor != null? armor.record(): null;
        return new PlayerRecord(name, health, Role.soldier, weaponRecord, armorRecord);
    }
}
