package me.trotyl.arena.formatter;

import me.trotyl.arena.effect.Type;
import me.trotyl.arena.procedure.EffectProcedure;


public class EffectFormatter extends Formatter<EffectProcedure> {

    @Override
    public String format(EffectProcedure procedure) {
        if (procedure.effect.type.equals(Type.toxin)) {
            return String.format("%s受到%d点毒性伤害, %s剩余生命: %d",
                    procedure.host.getName(), procedure.damage.extent,
                    procedure.host.getName(), procedure.host.getHealth());
        }

        if (procedure.effect.type.equals(Type.flame)) {
            return String.format("%s受到%d点火焰伤害, %s剩余生命: %d",
                    procedure.host.getName(), procedure.damage.extent,
                    procedure.host.getName(), procedure.host.getHealth());
        }

        if (procedure.effect.type.equals(Type.freeze)) {
            return String.format("%s冻得直哆嗦, 没有击中%s", procedure.host.getName(), procedure.remote.getName());
        }

        if (procedure.effect.type.equals(Type.swoon)) {
            return String.format("%s晕倒了, 无法攻击, 眩晕还剩: %d轮",
                    procedure.host.getName(), procedure.effect.remain - 1);
        }

        return null;
    }
}
