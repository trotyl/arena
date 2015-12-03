package me.trotyl.arena.record;


import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.effect.Type;

public class EffectRecord implements Record {

    public static final EffectRecord none = new EffectRecord(PlayerRecord.none, Type.none, 0);
    private AttackableRecord attackable;
    private Type type;
    public DamageRecord damage;
    private int remain;

    public EffectRecord(AttackableRecord attackable, Type type, int extent) {
        this(attackable, type, extent, -1);
    }

    public EffectRecord(AttackableRecord attackable, Type type, int extent, int remain) {
        this.attackable = attackable;
        this.type = type;
        this.damage = new DamageRecord(Genre.effect, extent);
        this.remain = remain;
    }

    @Override
    public String name() {
        return type.toString();
    }
}
