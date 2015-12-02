package me.trotyl.arena.role;


import me.trotyl.arena.status.AttackableStatus;

public interface Attackable {

    public int suffer(int injury);
    public AttackableStatus status();
}
