package me.trotyl.arena.role;


import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.record.AttackerRecord;

public interface Attacker {

    public int aggressivity();
    public AttackProcedure attack(Attackable attackable);
    public AttackerRecord record();
}
