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
    public AttackProcedure attack(Player player) {
        int damage = aggressivity + weapon.aggressivity();

        damage = player.suffer(damage);

        return new AttackProcedure(status(), player.status(), damage);
    }

    @Override
    public int suffer(int damage) {
        if (damage > armor.defence()) {
            health -= (damage - armor.defence());
        }
        return damage - armor.defence();
    }

    @Override
    public PlayerStatus status() {
        WeaponStatus weaponStatus = weapon != null? weapon.status(): null;
        ArmorStatus armorStatus = armor != null? armor.status(): null;
        return new PlayerStatus(name, health, Role.soldier, weaponStatus, armorStatus);
    }
}
