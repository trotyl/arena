package me.trotyl.arena.parser;


public abstract class Parser<TSource, TResult> {

    public abstract TResult parse(TSource jsonToken);
}
