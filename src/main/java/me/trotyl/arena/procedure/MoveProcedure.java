package me.trotyl.arena.procedure;


import me.trotyl.arena.record.AttackableRecord;
import me.trotyl.arena.record.AttackerRecord;
import me.trotyl.arena.record.PlayerRecord;

public class MoveProcedure extends Procedure {

    public static final MoveProcedure none = new MoveProcedure(0, PlayerRecord.none, PlayerRecord.none);

    public static MoveProcedure create(int decrement, AttackerRecord attacker, AttackableRecord attackable) {

        if (decrement == 0) {
            return none;
        }
        
        return new MoveProcedure(decrement, attacker, attackable);
    }

    public final int decrement;
    public final AttackerRecord attacker;
    public final AttackableRecord attackable;

    protected MoveProcedure(int decrement, AttackerRecord attacker, AttackableRecord attackable) {

        this.decrement = decrement;
        this.attacker = attacker;
        this.attackable = attackable;
    }
}
