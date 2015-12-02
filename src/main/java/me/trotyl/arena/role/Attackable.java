package me.trotyl.arena.role;


import me.trotyl.arena.status.AttackableRecord;

public interface Attackable {

    public int suffer(int injury);
    public AttackableRecord record();
}
