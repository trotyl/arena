package me.trotyl.arena.effect;


import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.record.Action;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Player;

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
    public int rein(Player player) {
        return frozen? 0: player.getVelocity();
    }

    @Override
    public DamageRecord sway(Player attacker, Player defender, AggressiveAttribute attribute) {
        return frozen ? DamageRecord.none : super.sway(attacker, defender, attribute);
    }

    @Override
    public boolean valid() {

        remain--;
        frozen = !frozen;

        return remain > 0;
    }

    protected Genre getGenre() {
        return Genre.freezing;
    }
}
