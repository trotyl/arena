package me.trotyl.arena;


import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.effect.Type;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.record.WeaponRecord;
import me.trotyl.arena.role.Role;

import static java.lang.String.format;

public class Formatter {

    public String formatAttack(AttackProcedure procedure) {

        if (procedure.equals(AttackProcedure.none)) {
            return null;
        }

        String weaponPart = (procedure.attacker.getWeapon() != WeaponRecord.none) ?
                format("用%s", procedure.attacker.getWeapon().getName()) : "";

        String attributePart = formatAttribute(procedure);

        return format("%s%s%s攻击了%s%s, %s%s剩余生命: %d",
                formatRole(procedure.attacker.getRole()), procedure.attacker.getName(), weaponPart,
                formatRole(procedure.attackable.getRole()), procedure.attackable.getName(),
                attributePart, procedure.attackable.getName(), procedure.attackable.getHealth());
    }

    private String formatAttribute(AttackProcedure procedure) {

        String damagePart = format("%s受到了%d点伤害, ", procedure.attackable.getName(), procedure.damage.extent);

        if (procedure.damage.genre.equals(Genre.none)) {
            return damagePart;
        }

        if (procedure.damage.genre.equals(Genre.striking)) {
            return format("%s发动了全力一击, %s", procedure.attacker.getName(), damagePart);
        }

        String attributePart = procedure.damage.genre.equals(Genre.toxic)? "中毒了":
                               procedure.damage.genre.equals(Genre.flaming)? "着火了":
                               procedure.damage.genre.equals(Genre.freezing)? "冻僵了":
                               procedure.damage.genre.equals(Genre.dizzy)? "晕倒了": "";

        return format("%s%s%s, ", damagePart, procedure.attackable.getName(), attributePart);
    }

    public String formatEffect(EffectProcedure procedure) {

        if (procedure.effect.type.equals(Type.toxin)) {
            return format("%s受到%d点毒性伤害, %s剩余生命: %d",
                    procedure.host.getName(), procedure.damage.extent,
                    procedure.host.getName(), procedure.host.getHealth());
        }

        if (procedure.effect.type.equals(Type.flame)) {
            return format("%s受到%d点火焰伤害, %s剩余生命: %d",
                    procedure.host.getName(), procedure.damage.extent,
                    procedure.host.getName(), procedure.host.getHealth());
        }

        if (procedure.effect.type.equals(Type.freeze)) {
            return format("%s冻得直哆嗦, 没有击中%s", procedure.host.getName(), procedure.remote.getName());
        }

        if (procedure.effect.type.equals(Type.swoon)) {
            return format("%s晕倒了, 无法攻击, 眩晕还剩: %d轮",
                    procedure.host.getName(), procedure.effect.remain - 1);
        }

        return null;
    }

    public String formatOver(OverProcedure procedure) {
        return format("%s被打败了.", procedure.loser.getName());
    }

    private String formatRole(Role role) {
        return role.equals(Role.assassin)? "刺客":
                role.equals(Role.fighter)? "战士":
                role.equals(Role.knight)? "骑士": "普通人";
    }
}
