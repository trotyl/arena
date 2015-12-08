package me.trotyl.arena.role;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.MoveProcedure;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;
import org.javatuples.Triplet;

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

    protected final String name;
    protected int health;
    protected final int aggressivity;
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

    public Attribute getAttribute() {
        return Attribute.none;
    }

    @Override
    public int getDefence() {
        return 0;
    }

    public Effect getEffect() {
        return effect;
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
    public Triplet<EffectProcedure, MoveProcedure, AttackProcedure> action(Attackable attackable, int distance) {

        EffectProcedure effect = impact(attackable);

        if (!alive()) {
            return new Triplet<>(effect, MoveProcedure.none, AttackProcedure.none);
        }

        MoveProcedure move = MoveProcedure.none;
        AttackProcedure attack = AttackProcedure.none;

        if (getRange() < distance) {
            move = MoveProcedure.create(getVelocity(), record(), attackable.record());
        } else {
            attack = attack(attackable);
        }

        if (!this.effect.valid()) {
            this.effect = Effect.none;
        }

        return new Triplet<>(effect, move, attack);
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

    protected AttackProcedure attack(Attackable attackable) {

        DamageRecord attackDamage = effect.sway(this, attackable, getAttribute());

        return AttackProcedure.create(record(), attackable.record(), attackDamage);
    }

    protected int getRange() {
        return 1;
    }

    protected int getVelocity() {
        return 1;
    }

    protected EffectProcedure impact(Attackable attackable) {

        DamageRecord effectDamage = effect.take(this);

        return EffectProcedure.create(record(), attackable.record(), effect.record(), effectDamage);
    }
}
