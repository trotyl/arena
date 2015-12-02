package me.trotyl.arena.role;


import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.status.AttackerRecord;

public interface Attacker {

    public AttackProcedure attack(Attackable attackable);
    public AttackerRecord record();
}
