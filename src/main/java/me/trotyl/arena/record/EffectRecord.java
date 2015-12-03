package me.trotyl.arena.record;


import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.effect.Type;

public class EffectRecord implements Record {

    public static final EffectRecord none = new EffectRecord(Type.none, 0);
    private Type type;
    public DamageRecord damage;
    private int remain;

    public EffectRecord(Type type, int extent) {
        this(type, extent, -1);
    }

    public EffectRecord(Type type, int extent, int remain) {

        this.type = type;
        this.damage = new DamageRecord(Genre.effect, extent);
        this.remain = remain;
    }

    @Override
    public String name() {
        return type.toString();
    }
}
