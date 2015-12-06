package me.trotyl.arena.procedure;


public class ActionProcedure extends Procedure {

    public static final ActionProcedure none = new ActionProcedure(MoveProcedure.none, AttackProcedure.none);

    public static ActionProcedure create(MoveProcedure move, AttackProcedure attack) {
        return new ActionProcedure(move, attack);
    }

    public MoveProcedure move;
    public AttackProcedure attack;

    protected ActionProcedure(MoveProcedure move, AttackProcedure attack) {

        this.move = move;
        this.attack = attack;
    }
}
