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

        String attributePart = procedure.damage.genre.equals(Genre.toxic)? "中毒了":
                procedure.damage.genre.equals(Genre.flaming)? "着火了":
                        procedure.damage.genre.equals(Genre.freezing)? "冻僵了":
                                procedure.damage.genre.equals(Genre.dizzy)? "晕倒了": "";

        return String.format("%s%s%s, ", damagePart, procedure.attackable.getName(), attributePart);
    }

    private String formatRole(Role role) {
        return role.equals(Role.assassin)? "刺客":
                role.equals(Role.fighter)? "战士":
                        role.equals(Role.knight)? "骑士": "普通人";
    }
}
