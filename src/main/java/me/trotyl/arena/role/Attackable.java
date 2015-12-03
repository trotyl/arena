package me.trotyl.arena.role;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.AttackableRecord;

public interface Attackable {

    public int suffer(int injury);
    public int suffer(int injury, Effect effect);
    public AttackableRecord record();
    public int defence();
}
