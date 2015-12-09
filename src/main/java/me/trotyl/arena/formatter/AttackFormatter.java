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

        String innerPart = formatInner(procedure.damage, procedure.attacker, procedure.defender);

        String statusPart = formatStatus(procedure.damage, procedure.attacker, procedure.defender);

        return String.format("%s%s%s攻击了%s%s, %s%s",
                formatRole(procedure.attacker.getRole()), procedure.attacker.getName(), weaponPart,
                formatRole(procedure.defender.getRole()), procedure.defender.getName(),
                innerPart, statusPart);
    }


    private String formatAttribute(DamageRecord damage, AttackerRecord attacker, AttackableRecord defender) {

        switch (damage.genre) {
            case none:
                return "";
            case striking:
                return String.format("%s发动了全力一击, ", attacker.getName());
            case repel:
                return String.format("%s被击退了, ", defender.getName());
            case carom:
                return String.format("%s发动了连击, ", attacker.getName());
            case counter:
                return String.format("%s发动了格挡反击, ", defender.getName());
            case toxic:
                return String.format("%s中毒了, ", defender.getName());
            case flaming:
                return String.format("%s着火了, ", defender.getName());
            case freezing:
                return String.format("%s冻僵了, ", defender.getName());
            case dizzy:
                return String.format("%s晕倒了, ", defender.getName());
            default:
                throw new IllegalArgumentException("The genre is invalid in ths context.");
        }
    }

    private String formatInner(DamageRecord damage, PlayerRecord attacker, PlayerRecord defender) {

        String damagePart = String.format("%s受到了%d点伤害, ", defender.getName(), damage.extent);
        String attributePart = formatAttribute(damage, attacker, defender);

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
                String innerPart = formatInner(innerDamage, attacker, defender);
                return String.format("%s%s", innerPart, attributePart);
            case carom:
                DamageRecord firstDamage = ((CaromDamageRecord) damage).first;
                DamageRecord secondDamage = ((CaromDamageRecord) damage).second;
                String firstInnerPart = formatInner(firstDamage, attacker, defender);
                String secondInnerPart = formatInner(secondDamage, attacker, defender);
                return String.format("%s%s%s", firstInnerPart, attributePart, secondInnerPart);
            case counter:
                DamageRecord original = ((CounterDamageRecord) damage).original;
                DamageRecord counter = ((CounterDamageRecord) damage).counter;
                String originalInnerPart = formatInner(original, attacker, defender);
                String counterInnerPart;
                if (counter.equals(DamageRecord.none)) {
                    counterInnerPart = String.format("%s不在攻击范围内, ", attacker.getName());
                } else {
                    counterInnerPart = formatInner(counter, defender, attacker);
                }
                return String.format("%s%s%s", originalInnerPart, attributePart, counterInnerPart);
            default:
                throw new IllegalArgumentException("The genre is invalid in ths context.");
        }
    }

    private String formatStatus(DamageRecord damage, PlayerRecord attacker, PlayerRecord defender) {

        switch (damage.genre) {
            case counter:
                return String.format("%s剩余生命: %d, %s剩余生命: %d",
                        defender.getName(), defender.getHealth(),
                        attacker.getName(), attacker.getHealth());
            case carom:
                DamageRecord first = ((CaromDamageRecord) damage).first;
                DamageRecord second = ((CaromDamageRecord) damage).second;
                switch (first.genre) {
                    case counter:
                        return formatStatus(first, attacker, defender);
                    case carom:
                        return formatStatus(first, attacker, defender);
                }
                switch (second.genre) {
                    case counter:
                        return formatStatus(first, attacker, defender);
                    case carom:
                        return formatStatus(first, attacker, defender);
                }
            default:
                return String.format("%s剩余生命: %d",
                        defender.getName(), defender.getHealth());
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
