package me.trotyl.arena.formatter;

import me.trotyl.arena.procedure.EffectProcedure;


public class EffectFormatter extends Formatter<EffectProcedure> {

    @Override
    public String format(EffectProcedure procedure) {

        switch (procedure.effect.genre) {
            case none:
                return null;
            case toxic:
                return String.format("%s受到%d点毒性伤害, %s剩余生命: %d",
                        procedure.host.getName(), procedure.damage.extent,
                        procedure.host.getName(), procedure.host.getHealth());
            case flaming:
                return String.format("%s受到%d点火焰伤害, %s剩余生命: %d",
                        procedure.host.getName(), procedure.damage.extent,
                        procedure.host.getName(), procedure.host.getHealth());
            case freezing:
                switch (procedure.effect.next) {
                    case move:
                        return String.format("%s冻得直哆嗦, 没有移动", procedure.host.getName());
                    case attack:
                        return String.format("%s冻得直哆嗦, 没有击中%s", procedure.host.getName(), procedure.remote.getName());
                    default:
                        throw new IllegalArgumentException("The action is invalid in ths context.");
                }
            case dizzy:
                switch (procedure.effect.next) {
                    case move:
                        return String.format("%s晕倒了, 无法移动, 眩晕还剩: %d轮",
                                procedure.host.getName(), procedure.effect.remain - 1);
                    case attack:
                        return String.format("%s晕倒了, 无法攻击, 眩晕还剩: %d轮",
                                procedure.host.getName(), procedure.effect.remain - 1);
                    default:
                        throw new IllegalArgumentException("The action is invalid in ths context.");
                }

            default:
                throw new IllegalArgumentException("The genre is invalid in ths context.");
        }
    }
}
