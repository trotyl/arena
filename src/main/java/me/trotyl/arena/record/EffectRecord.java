package me.trotyl.arena.record;


import me.trotyl.arena.effect.Type;

public class EffectRecord implements Record {

    public static final EffectRecord none = new EffectRecord( Type.none, 0);
    public Type type;
    public int remain;

    public EffectRecord(Type type) {
        this(type, -1);
    }

    public EffectRecord(Type type, int remain) {
        this.type = type;
        this.remain = remain;
    }

    @Override
    public String name() {
        return type.toString();
    }
}
