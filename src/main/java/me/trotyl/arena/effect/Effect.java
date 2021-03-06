package me.trotyl.arena.effect;


import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.DefensiveAttribute;
import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.record.Action;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.role.Player;

public abstract class Effect {

    public static final Effect none = new Effect(0) {

        @Override
        public boolean valid() {
            return true;
        }
    };

    protected int remain;

    protected Effect(int remain) {
        this.remain = remain;
    }

    public int getRemain() {
        return remain;
    }

    public void append(int remain) {
        this.remain += remain;
    }

    public EffectRecord record(Action next) {
        return EffectRecord.create(getGenre(), getRemain(), next);
    }

    public DamageRecord sway(Player attacker, Player defender, AggressiveAttribute attribute) {

        DefensiveAttribute defensive = defender.getDefensiveAttribute();

        return attribute.apply(attacker, defender, Attribute.normalAttack, defensive);
    }

    public DamageRecord take(Player player) {
        return DamageRecord.none;
    }

    public boolean valid() {

        remain --;

        return remain > 0;
    }

    protected Genre getGenre() {
        return Genre.none;
    }

    public int rein(Player player) {
        return player.getVelocity();
    }
}
