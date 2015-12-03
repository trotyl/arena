package me.trotyl.arena;


import me.trotyl.arena.attribute.Genre;
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

        String attributePart = formatAttribute(procedure);

        return format("%s%s%s攻击了%s%s, %s%s剩余生命: %d",
                formatRole(procedure.attacker.role()), procedure.attacker.name(), weaponPart,
                formatRole(procedure.attackable.role()), procedure.attackable.name(),
                attributePart, procedure.attackable.name(), procedure.attackable.health());
    }

    private String formatAttribute(AttackProcedure procedure) {
        String damagePart = format("%s受到了%d点伤害, ", procedure.attackable.name(), procedure.damage.extent);

        if (procedure.damage.genre.equals(Genre.none)) {
            return damagePart;
        }

        if (procedure.damage.genre.equals(Genre.striking)) {
            return format("%s发动了全力一击, %s", procedure.attacker.name(), damagePart);
        }

        String attributePart = procedure.damage.genre.equals(Genre.toxic)? "中毒了":
                               procedure.damage.genre.equals(Genre.flaming)? "着火了":
                               procedure.damage.genre.equals(Genre.freezing)? "冻僵了":
                               procedure.damage.genre.equals(Genre.dizzy)? "晕倒了": "";

        return format("%s%s%s, ", damagePart, procedure.attackable.name(), attributePart);
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

    private String formatRole(Role role) {
        return role.equals(Role.assassin)? "刺客":
                role.equals(Role.fighter)? "战士":
                role.equals(Role.knight)? "骑士": "普通人";
    }
}
