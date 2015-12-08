package me.trotyl.arena.formatter;


public class FormatterGroup {

    public final AttackFormatter attackFormatter;
    public final EffectFormatter effectFormatter;
    public final MoveFormatter moveFormatter;
    public final OverFormatter overFormatter;

    public FormatterGroup(AttackFormatter attackFormatter,
                          EffectFormatter effectFormatter,
                          MoveFormatter moveFormatter,
                          OverFormatter overFormatter) {

        this.attackFormatter = attackFormatter;
        this.effectFormatter = effectFormatter;
        this.moveFormatter = moveFormatter;
        this.overFormatter = overFormatter;
    }
}
