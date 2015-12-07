package me.trotyl.arena.formatter;

import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.procedure.ActionProcedure;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.record.WeaponRecord;
import me.trotyl.arena.role.Role;


public class ActionFormatter extends Formatter<ActionProcedure> {

    @Override
    public String format(ActionProcedure procedure) {
        if (procedure.attack.equals(AttackProcedure.none)) {
            return null;
        }

        String weaponPart = (procedure.attack.attacker.getWeapon() != WeaponRecord.none) ?
                String.format("用%s", procedure.attack.attacker.getWeapon().getName()) : "";

        String attributePart = formatAttribute(procedure.attack);

        return String.format("%s%s%s攻击了%s%s, %s%s剩余生命: %d",
                formatRole(procedure.attack.attacker.getRole()), procedure.attack.attacker.getName(), weaponPart,
                formatRole(procedure.attack.attackable.getRole()), procedure.attack.attackable.getName(),
                attributePart, procedure.attack.attackable.getName(), procedure.attack.attackable.getHealth());
    }


    private String formatAttribute(AttackProcedure procedure) {

        String damagePart = String.format("%s受到了%d点伤害, ", procedure.attackable.getName(), procedure.damage.extent);

        if (procedure.damage.genre.equals(Genre.none)) {
            return damagePart;
        }

        if (procedure.damage.genre.equals(Genre.striking)) {
            return String.format("%s发动了全力一击, %s", procedure.attacker.getName(), damagePart);
        }

        String statusPart;

        switch (procedure.damage.genre) {
            case none:
                return damagePart;
            case striking:
                return String.format("%s发动了全力一击, %s", procedure.attacker.getName(), damagePart);
            case repel:
                return String.format("%s%s被击退了, ", damagePart, procedure.attackable.getName());
            case toxic:
                statusPart = "中毒了";
                break;
            case flaming:
                statusPart = "着火了";
                break;
            case freezing:
                statusPart = "冻僵了";
                break;
            case dizzy:
                statusPart = "晕倒了";
                break;
            default:
                throw new IllegalArgumentException("The genre is invalid in ths context.");
        }

        return String.format("%s%s%s, ", damagePart, procedure.attackable.getName(), statusPart);
    }

    private String formatRole(Role role) {

        switch (role) {
            case normal:
                return "普通人";
            case assassin:
                return "刺客";
            case fighter:
                return "战士";
            case knight:
                return "骑士";
            default:
                throw new IllegalArgumentException("The genre is invalid in ths context.");
        }
    }
}
