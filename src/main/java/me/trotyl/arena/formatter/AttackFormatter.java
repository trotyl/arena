package me.trotyl.arena.formatter;

import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.record.*;
import me.trotyl.arena.role.Role;


public class AttackFormatter extends Formatter<AttackProcedure> {

    @Override
    public String format(AttackProcedure procedure) {
        if (procedure.equals(AttackProcedure.none)) {
            return null;
        }

        String weaponPart = (procedure.attacker.getWeapon() != WeaponRecord.none) ?
                String.format("用%s", procedure.attacker.getWeapon().getName()) : "";

        String attributePart = formatInner(procedure.damage,
                procedure.attacker,
                procedure.attackable);

        return String.format("%s%s%s攻击了%s%s, %s%s剩余生命: %d",
                formatRole(procedure.attacker.getRole()), procedure.attacker.getName(), weaponPart,
                formatRole(procedure.attackable.getRole()), procedure.attackable.getName(),
                attributePart, procedure.attackable.getName(), procedure.attackable.getHealth());
    }

    private String formatAttribute(DamageRecord damage, AttackerRecord attacker, AttackableRecord attackable) {

        switch (damage.genre) {
            case none:
                return "";
            case striking:
                return String.format("%s发动了全力一击, ", attacker.getName());
            case repel:
                return String.format("%s被击退了, ", attackable.getName());
            case carom:
                return String.format("%s发动了连击, ", attacker.getName());
            case toxic:
                return String.format("%s中毒了, ", attackable.getName());
            case flaming:
                return String.format("%s着火了, ", attackable.getName());
            case freezing:
                return String.format("%s冻僵了, ", attackable.getName());
            case dizzy:
                return String.format("%s晕倒了, ", attackable.getName());
            default:
                throw new IllegalArgumentException("The genre is invalid in ths context.");
        }
    }

    private String formatInner(DamageRecord damage, AttackerRecord attacker, AttackableRecord attackable) {

        String damagePart = String.format("%s受到了%d点伤害, ", attackable.getName(), damage.extent);
        String attributePart = formatAttribute(damage, attacker, attackable);

        switch (damage.genre) {
            case none:
                return damagePart;
            case striking:
                return String.format("%s%s", attributePart, damagePart);
            case toxic:
            case flaming:
            case freezing:
            case dizzy:
                return String.format("%s%s", damagePart, attributePart);
            case repel:
                DamageRecord innerDamage = ((RepelDamageRecord) damage).inner;
                String innerPart = formatInner(innerDamage, attacker, attackable);
                return String.format("%s%s", innerPart, attributePart);
            case carom:
                DamageRecord firstDamage = ((CaromDamageRecord) damage).first;
                DamageRecord secondDamage = ((CaromDamageRecord) damage).second;
                String firstInnerPart = formatInner(firstDamage, attacker, attackable);
                String secondInnerPart = formatInner(secondDamage, attacker, attackable);
                return String.format("%s%s%s", firstInnerPart, attributePart, secondInnerPart);
            default:
                throw new IllegalArgumentException("The genre is invalid in ths context.");
        }
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
