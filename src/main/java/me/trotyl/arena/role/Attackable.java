package me.trotyl.arena.role;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.AttackableRecord;

public interface Attackable {

    public int getDefence();

    public AttackableRecord record();
    public void suffer(int injury, Effect effect);
}
