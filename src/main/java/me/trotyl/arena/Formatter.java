package me.trotyl.arena;


import me.trotyl.arena.effect.Type;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.WeaponRecord;
import me.trotyl.arena.role.Role;

import static java.lang.String.format;

public class Formatter {

    public String formatAttack(AttackProcedure procedure) {
        if (procedure.damage.equals(DamageRecord.none)) {
            return null;
        }

        String weaponPart = (procedure.attacker.weapon() != WeaponRecord.none) ?
                format("用%s", procedure.attacker.weapon().name()) : "";

        return format("%s%s%s攻击了%s%s, %s受到了%d点伤害, %s剩余生命: %d",
                formatRole(procedure.attacker.role()), procedure.attacker.name(), weaponPart,
                formatRole(procedure.attackable.role()), procedure.attackable.name(),
                procedure.attackable.name(), procedure.damage.extent,
                procedure.attackable.name(), procedure.attackable.health());
    }

    public String formatOver(OverProcedure procedure) {
        return format("%s被打败了.", procedure.loser.name());
    }

    public String formatEffect(EffectProcedure procedure) {
        if (procedure.effect.type.equals(Type.toxin)) {
            return format("%s受到%d点毒性伤害, %s剩余生命: %d",
                    procedure.attackable.name(), procedure.damage.extent,
                    procedure.attackable.name(), procedure.attackable.health());
        }

        if (procedure.effect.type.equals(Type.fire)) {
            return format("%s受到%d点火焰伤害, %s剩余生命: %d",
                    procedure.attackable.name(), procedure.damage.extent,
                    procedure.attackable.name(), procedure.attackable.health());
        }

        if (procedure.effect.type.equals(Type.freeze)) {
            return format("%s冻僵了, 无法攻击.", procedure.attackable.name());
        }

        if (procedure.effect.type.equals(Type.swoon)) {
            return format("%s晕倒了, 无法攻击, 眩晕还剩: %d轮",
                    procedure.attackable.name(), procedure.effect.remain);
        }

        return null;
    }

    public String formatRole(Role role) {
        if (role.equals(Role.soldier)) {
            return "战士";
        }
        return "普通人";
    }
}
