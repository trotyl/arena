package me.trotyl.arena.player;


import me.trotyl.arena.Weapon;
import me.trotyl.arena.procedure.AttackProcedure;

public class Soldier extends Player {
    private Weapon weapon;

    public Soldier(String name, int health, int aggressivity) {
        super(name, health, aggressivity);
    }

    public void equip(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public AttackProcedure attack(Player player) {
        int damage = aggressivity + weapon.aggressivity();

        player.suffer(damage);

        return new AttackProcedure(status(), player.status(), damage);
    }
}
