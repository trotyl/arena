package me.trotyl.arena.role;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.equipment.Armor;
import me.trotyl.arena.equipment.Length;
import me.trotyl.arena.equipment.Weapon;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.MoveProcedure;
import me.trotyl.arena.record.Action;
import me.trotyl.arena.record.PlayerRecord;
import org.javatuples.Triplet;

public class Knight extends Soldier {

    public static Knight create(String name, int health, int aggressivity) {
        return Knight.create(name, health, aggressivity, Weapon.none, Armor.none);
    }

    public static Knight create(String name, int health, int aggressivity, Weapon weapon, Armor armor) {

        checkParameters(name, health, aggressivity);

        if (weapon == null) {
            weapon = Weapon.none;
        } if (armor == null) {
            armor = Armor.none;
        }

        return new Knight(name, health, aggressivity, weapon, armor);
    }

    protected Knight(String name, int health, int aggressivity, Weapon weapon, Armor armor) {
        super(name, health, aggressivity, weapon, armor);
    }

    @Override
    public Triplet<EffectProcedure, MoveProcedure, AttackProcedure> action(Attackable attackable, int distance) {

        Action action = getRange() < distance? Action.move: Action.attack;
        EffectProcedure effect = impact(attackable, action);

        if (!alive()) {
            return new Triplet<>(effect, MoveProcedure.none, AttackProcedure.none);
        }

        MoveProcedure move = MoveProcedure.none;

        if (action.equals(Action.move)) {
            move = move(attackable);
        }

        AttackProcedure attack = attack(attackable);


        if (!this.effect.valid()) {
            this.effect = Effect.none;
        }

        return new Triplet<>(effect, move, attack);
    }

    @Override
    public Attribute getAttribute() {

        if (!weapon.getLength().equals(Length.longer)) {
            return Attribute.none;
        }

        return weapon.getAttribute();
    }

    @Override
    public int getVelocity() {
        return 2;
    }

    @Override
    public void equip(Weapon weapon) {

        if (weapon != Weapon.none &&
            weapon.getLength() != Length.longer &&
            weapon.getLength() != Length.medium) {
            throw new IllegalArgumentException("Knight can only equip long weapon!");
        }

        super.equip(weapon);
    }

    @Override
    public PlayerRecord record() {
        return PlayerRecord.create(name, health, Role.knight, weapon.record(), armor.record());
    }
}
