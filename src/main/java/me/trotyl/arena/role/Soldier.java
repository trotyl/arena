package me.trotyl.arena.role;


import me.trotyl.arena.Armor;
import me.trotyl.arena.Weapon;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.status.ArmorStatus;
import me.trotyl.arena.status.PlayerStatus;
import me.trotyl.arena.status.WeaponStatus;

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
        int damage = attackable.suffer(aggressivity + weapon.aggressivity());

        return new AttackProcedure(status(), attackable.status(), damage);
    }

    @Override
    public int suffer(int injury) {
        if (injury > armor.defence()) {
            health -= (injury - armor.defence());
        }
        return injury - armor.defence();
    }

    @Override
    public PlayerStatus status() {
        WeaponStatus weaponStatus = weapon != null? weapon.status(): null;
        ArmorStatus armorStatus = armor != null? armor.status(): null;
        return new PlayerStatus(name, health, Role.soldier, weaponStatus, armorStatus);
    }
}
