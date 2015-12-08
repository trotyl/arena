package me.trotyl.arena.role;


import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.DefensiveAttribute;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.MoveProcedure;
import me.trotyl.arena.record.Action;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;
import org.javatuples.Triplet;

public class Player {

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

    public int getAggressivity() {
        return aggressivity;
    }

    public AggressiveAttribute getAggressiveAttribute() {
        return Attribute.normalAttack;
    }

    public int getDefence() {
        return 0;
    }

    public DefensiveAttribute getDefensiveAttribute() {
        return Attribute.normalDefence;
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

    public int getVelocity() {
        return 1;
    }

    public boolean alive() {
        return health > 0;
    }

    public Triplet<EffectProcedure, MoveProcedure, AttackProcedure> action(Player defender, int distance) {

        Action action = getRange() < distance? Action.move: Action.attack;
        EffectProcedure effect = impact(defender, action);

        if (!alive()) {
            return new Triplet<>(effect, MoveProcedure.none, AttackProcedure.none);
        }

        MoveProcedure move = MoveProcedure.none;
        AttackProcedure attack = AttackProcedure.none;

        if (action.equals(Action.move)) {
            move = move(defender);
        } else {
            attack = attack(defender);
        }

        if (!this.effect.valid()) {
            this.effect = Effect.none;
        }

        return new Triplet<>(effect, move, attack);
    }

    public PlayerRecord record() {
        return PlayerRecord.create(name, health);
    }

    public void suffer(int damage, Effect effect) {

        health -= damage;

        if (effect.getClass().equals(this.effect.getClass())) {
            this.effect.append(effect.getRemain());
        } else if (!effect.equals(Effect.none)) {
            this.effect = effect;
        }
    }

    protected AttackProcedure attack(Player defender) {

        DamageRecord attackDamage = effect.sway(this, defender, getAggressiveAttribute());

        return AttackProcedure.create(record(), defender.record(), attackDamage);
    }

    protected int getRange() {
        return 1;
    }

    protected EffectProcedure impact(Player defender, Action next) {

        DamageRecord effectDamage = effect.take(this);

        return EffectProcedure.create(record(), defender.record(), effect.record(next), effectDamage);
    }

    protected MoveProcedure move(Player defender) {

        int distance = effect.rein(this);

        return MoveProcedure.create(distance, record(), defender.record());
    }
}
