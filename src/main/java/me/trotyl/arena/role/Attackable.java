package me.trotyl.arena.role;


import me.trotyl.arena.record.AttackableRecord;

public interface Attackable {

    public int suffer(int injury);
    public AttackableRecord record();
}
