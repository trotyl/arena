package me.trotyl.arena.formatter;


import me.trotyl.arena.procedure.MoveProcedure;

public class MoveFormatter extends Formatter<MoveProcedure> {

    @Override
    public String format(MoveProcedure procedure) {

        if (procedure.equals(MoveProcedure.none)) {
            return null;
        }

        return String.format("%s靠近了%s", procedure.attacker.getName(), procedure.attackable.getName());
    }
}
