package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Swoon;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Dizzy extends Attribute {

    public Dizzy(float rate) {
        super(2, rate);
    }

    @Override
    public DamageRecord apply(Attacker attacker, Attackable attackable) {
        if (random.nextFloat() > rate) {
            return Attribute.none.apply(attacker, attackable);
        }

        Swoon swoon = new Swoon(limit);
        int damage = attacker.aggressivity() - attackable.defence();
        attackable.suffer(damage, swoon);

        return new DamageRecord(Genre.dizzy, damage);
    }
}
