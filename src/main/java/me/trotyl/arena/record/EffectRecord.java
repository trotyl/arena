package me.trotyl.arena.record;


import me.trotyl.arena.effect.Type;

public class EffectRecord implements Record {

    public static final EffectRecord none = new EffectRecord(Type.none, 0);
    private Type type;
    private int extent;
    private int remain;

    public EffectRecord(Type type, int extent) {
        this(type, extent, -1);
    }

    public EffectRecord(Type type, int extent, int remain) {

        this.type = type;
        this.extent = extent;
        this.remain = remain;
    }

    @Override
    public String name() {
        return type.toString();
    }
}
