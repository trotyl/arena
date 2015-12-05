package me.trotyl.arena.record;


import me.trotyl.arena.effect.Type;

public class EffectRecord implements Record {

    public static final EffectRecord none = new EffectRecord(Type.none, -1);

    public static EffectRecord create(Type type) {
        return new EffectRecord(type, -1);
    }

    public static EffectRecord create(Type type, int remain) {
        return new EffectRecord(type, remain);
    }

    public Type type;
    public int remain;

    protected EffectRecord(Type type, int remain) {

        this.type = type;
        this.remain = remain;
    }

    @Override
    public String getName() {
        return type.toString();
    }
}
