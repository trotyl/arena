package me.trotyl.arena.role;


import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.status.PlayerStatus;

public class Player {

    protected String name;
    protected int health;
    protected int aggressivity;

    public Player(String name, int health, int aggressivity) {
        this.name = name;
        this.health = health;
        this.aggressivity = aggressivity;
    }

    public boolean alive() {
        return health > 0;
    }

    public AttackProcedure attack(Player player) {
        int damage = aggressivity;

        damage = player.suffer(damage);

        return new AttackProcedure(status(), player.status(), damage);
    }

    protected int suffer(int damage) {
        health -= damage;
        return damage;
    }

    public PlayerStatus status() {
        return new PlayerStatus(this);
    }

    public int health() {
        return health;
    }

    public String name() {
        return name;
    }
}
