package me.trotyl.arena;


import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.status.PlayerStatus;

public class Player {

    private String name;
    private int health;
    private int aggressivity;

    public Player(String name, int health, int aggressivity) {
        this.name = name;
        this.health = health;
        this.aggressivity = aggressivity;
    }

    public String getName() {
        return name;
    }

    public boolean alive() {
        return health > 0;
    }

    public AttackProcedure attack(Player another) {
        int damage = aggressivity;

        another.suffer(damage);

        return new AttackProcedure(status(), another.status(), damage);
    }

    private void suffer(int damage) {
        health -= damage;
    }

    public PlayerStatus status() {
        return new PlayerStatus(name, health);
    }
}
