package me.trotyl.arena.formatter;


public class FormatterGroup {

    public final ActionFormatter actionFormatter;
    public final EffectFormatter effectFormatter;
    public final OverFormatter overFormatter;

    public FormatterGroup(ActionFormatter actionFormatter,
                          EffectFormatter effectFormatter,
                          OverFormatter overFormatter) {

        this.actionFormatter = actionFormatter;
        this.effectFormatter = effectFormatter;
        this.overFormatter = overFormatter;
    }
}
