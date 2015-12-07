package me.trotyl.arena.formatter;

import me.trotyl.arena.procedure.OverProcedure;


public class OverFormatter extends Formatter<OverProcedure> {

    @Override
    public String format(OverProcedure procedure) {
        return String.format("%s被打败了.", procedure.loser.getName());
    }
}
