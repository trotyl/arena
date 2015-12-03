package me.trotyl.arena.role;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.AttackableRecord;

public interface Attackable {

    public void suffer(int injury);
    public void suffer(int injury, Effect effect);
    public AttackableRecord record();
    public int defence();
}
