package me.trotyl.arena.parser;


public abstract class Parser<TSource, TResult> {

    public abstract TResult parse(TSource jsonToken);

    public static ParserGroup defaults() {
        return new ParserGroup(new PlayerParser(new ArmorParser(), new WeaponParser(new AttributeParser())));
    }
}
