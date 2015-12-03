package me.trotyl.arena.role;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;

public class Player implements Attacker, Attackable {

    protected String name;
    protected int health;
    protected int aggressivity;
    protected Effect effect;

    public Player(String name, int health, int aggressivity) {
        this.name = name;
        this.health = health;
        this.aggressivity = aggressivity;
    }

    public boolean alive() {
        return health > 0;
    }

    @Override
    public void suffer(int injury) {
        suffer(injury, Effect.none);
    }

    @Override
    public void suffer(int damage, Effect effect) {
        health -= damage;
        this.effect = effect;
    }

    @Override
    public PlayerRecord record() {
        return new PlayerRecord(name, health, Role.normal);
    }

    @Override
    public int defence() {
        return 0;
    }

    @Override
    public int aggressivity() {
        return aggressivity;
    }

    @Override
    public AttackProcedure attack(Attackable attackable) {
        DamageRecord damage = Attribute.none.apply(this, attackable);
        return new AttackProcedure(record(), attackable.record(), damage);
    }
}
