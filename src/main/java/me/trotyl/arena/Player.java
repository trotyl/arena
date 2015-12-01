package me.trotyl.arena;


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

    public boolean isAlive() {
        return health > 0;
    }

    public void attack(Player another) {
        another.suffer(aggressivity);
    }

    private void suffer(int damage) {
        health -= damage;
    }
}
