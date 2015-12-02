package me.trotyl.arena.attribute;


import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public abstract class Attribute {

    public static Attribute none = new Attribute() {
        @Override
        public DamageRecord apply(Attacker attacker, Attackable attackable) {
            int damage = attacker.aggressivity() - attackable.defence();
            attackable.suffer(damage);

            return new DamageRecord(damage);
        }
    };

    public abstract DamageRecord apply(Attacker attacker, Attackable attackable);
}
