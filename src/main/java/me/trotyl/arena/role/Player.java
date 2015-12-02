package me.trotyl.arena.role;


import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.record.PlayerRecord;

public class Player implements Attacker, Attackable {

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

    public int suffer(int damage) {
        health -= damage;
        return damage;
    }

    @Override
    public PlayerRecord record() {
        return new PlayerRecord(name, health, Role.normal);
    }

    @Override
    public AttackProcedure attack(Attackable attackable) {
        int damage = attackable.suffer(aggressivity);
        return new AttackProcedure(record(), attackable.record(), damage);
    }
}
