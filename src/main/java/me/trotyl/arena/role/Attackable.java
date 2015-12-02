package me.trotyl.arena.role;


import me.trotyl.arena.status.AttackableStatus;

public interface Attackable {
    int suffer(int injury);
    AttackableStatus status();
}
