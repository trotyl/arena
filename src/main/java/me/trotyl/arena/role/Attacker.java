package me.trotyl.arena.role;


import me.trotyl.arena.procedure.AttackProcedure;

public interface Attacker {
    AttackProcedure attack(Attackable attackable);
}
