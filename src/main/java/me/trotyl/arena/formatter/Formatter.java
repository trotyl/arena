package me.trotyl.arena.formatter;


import me.trotyl.arena.procedure.Procedure;

public abstract class Formatter<T extends Procedure> {

    public static FormatterGroup defaults() {
        return new FormatterGroup(new ActionFormatter(), new EffectFormatter(), new OverFormatter());
    }

    public abstract String format(T procedure);
}
