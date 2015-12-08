package me.trotyl.arena.role;


import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.MoveProcedure;
import me.trotyl.arena.record.AttackerRecord;
import org.javatuples.Triplet;

public interface Attacker {

    public int getAggressivity();

    public Triplet<EffectProcedure, MoveProcedure, AttackProcedure> action(Attackable attackable, int distance);
    public AttackerRecord record();
}
