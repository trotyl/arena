package me.trotyl.arena.procedure;


public class MoveProcedure extends Procedure {

    public static final MoveProcedure none = new MoveProcedure(0);

    public static MoveProcedure create(int decrement) {
        return new MoveProcedure(decrement);
    }

    public final int decrement;

    public MoveProcedure(int decrement) {

        this.decrement = decrement;
    }
}
