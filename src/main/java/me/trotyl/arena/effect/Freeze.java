package me.trotyl.arena.effect;


import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.record.Action;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Attackable;
import me.trotyl.arena.role.Attacker;

public class Freeze extends Effect {

    public static Freeze create(int limit) {

        if (limit < 0) {
            throw new IllegalArgumentException("The extent must not be less than 0, but: " + limit);
        }

        return new Freeze(limit);
    }

    private boolean frozen;

    protected Freeze(int limit) {

        super(limit);

        frozen = true;
    }

    @Override
    public EffectRecord record(Action next) {
        return frozen? EffectRecord.create(getGenre(), getRemain(), next): EffectRecord.none;
    }

    @Override
    public DamageRecord sway(Attacker attacker, Attackable attackable, Attribute attribute) {

        remain--;
        DamageRecord damage = frozen ? DamageRecord.none :
                                       super.sway(attacker, attackable, attribute);

        frozen = !frozen;

        return damage;
    }

    protected Genre getGenre() {
        return Genre.freezing;
    }
}
