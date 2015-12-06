package me.trotyl.arena.role;


import me.trotyl.arena.procedure.ActionProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.AttackerRecord;
import org.javatuples.Pair;

public interface Attacker {

    public int getAggressivity();

    public Pair<EffectProcedure, ActionProcedure> action(Attackable attackable, int distance);
    public AttackerRecord record();
}
