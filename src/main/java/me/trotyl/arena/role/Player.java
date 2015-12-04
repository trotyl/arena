package me.trotyl.arena.role;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;
import org.javatuples.Pair;

public class Player implements Attacker, Attackable {

    public static Player create(String name, int health, int aggressivity) {
        return new Player(name, health, aggressivity);
    }

    protected String name;
    protected int health;
    protected int aggressivity;
    protected Effect effect;

    protected Player(String name, int health, int aggressivity) {
        this.name = name;
        this.health = health;
        this.aggressivity = aggressivity;
        this.effect = Effect.none;
    }

    public boolean alive() {
        return health > 0;
    }

    @Override
    public void suffer(int damage, Effect effect) {
        health -= damage;

        if (effect.equals(this.effect)) {
            this.effect.append(effect.remain());
        } else if (!effect.equals(Effect.none)) {
            this.effect = effect;
        }
    }

    @Override
    public PlayerRecord record() {
        return PlayerRecord.create(name, health);
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
    public Pair<EffectProcedure, AttackProcedure> attack(Attackable attackable) {
        return attackByAttribute(attackable, Attribute.none);
    }

    protected Pair<EffectProcedure, AttackProcedure> attackByAttribute(Attackable attackable, Attribute attribute) {

        DamageRecord effectDamage = effect.take(this);
        EffectProcedure effectProcedure = EffectProcedure.create(record(), effect.record(), effectDamage);
        DamageRecord attackDamage = effect.sway(this, attackable, attribute);
        AttackProcedure attackProcedure = AttackProcedure.create(record(), attackable.record(), attackDamage);

        if (!effect.valid()) {
            effect = Effect.none;
        }

        return new Pair<>(effectProcedure, attackProcedure);
    }

    public String name() {
        return name;
    }

    public int health() {
        return health;
    }
}
