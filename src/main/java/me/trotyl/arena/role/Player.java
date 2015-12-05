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

        checkParameters(name, health, aggressivity);

        return new Player(name, health, aggressivity);
    }

    protected static void checkParameters(String name, int health, int aggressivity) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name must be a valid string, but: " + name);
        } else if (health <= 0) {
            throw new IllegalArgumentException("The health must be greater than 0, but: " + health);
        } else if (aggressivity < 0) {
            throw new IllegalArgumentException("The aggressivity must not be less than 0, but: " + aggressivity);
        }
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

    @Override
    public int getAggressivity() {
        return aggressivity;
    }

    @Override
    public int getDefence() {
        return 0;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public boolean alive() {
        return health > 0;
    }

    @Override
    public Pair<EffectProcedure, AttackProcedure> attack(Attackable attackable) {
        return attackByAttribute(attackable, Attribute.none);
    }

    @Override
    public PlayerRecord record() {
        return PlayerRecord.create(name, health);
    }

    @Override
    public void suffer(int damage, Effect effect) {

        health -= damage;

        if (effect.getClass().equals(this.effect.getClass())) {
            this.effect.append(effect.getRemain());
        } else if (!effect.equals(Effect.none)) {
            this.effect = effect;
        }
    }

    protected Pair<EffectProcedure, AttackProcedure> attackByAttribute(Attackable attackable, Attribute attribute) {

        DamageRecord effectDamage = effect.take(this);
        EffectProcedure effectProcedure = EffectProcedure.create(record(), attackable.record(), effect.record(), effectDamage);

        if (!alive()) {
            return new Pair<>(effectProcedure, AttackProcedure.none);
        }

        DamageRecord attackDamage = effect.sway(this, attackable, attribute);
        AttackProcedure attackProcedure = AttackProcedure.create(record(), attackable.record(), attackDamage);

        if (!effect.valid()) {
            effect = Effect.none;
        }

        return new Pair<>(effectProcedure, attackProcedure);
    }
}
