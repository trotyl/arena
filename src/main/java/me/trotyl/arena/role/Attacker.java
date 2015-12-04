package me.trotyl.arena.role;


import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.AttackerRecord;
import org.javatuples.Pair;

public interface Attacker {

    public int aggressivity();

    public Pair<EffectProcedure, AttackProcedure> attack(Attackable attackable);
    public AttackerRecord record();
}
