package me.trotyl.arena.role;


import me.trotyl.arena.procedure.AttackProcedure;

public interface Attacker {

    public AttackProcedure attack(Attackable attackable);
}
