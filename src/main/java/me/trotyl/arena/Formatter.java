package me.trotyl.arena;


import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.record.WeaponRecord;

public class Formatter {

    public String formatAttack(AttackProcedure procedure) {
        String weaponPart = procedure.attacker.weapon() != WeaponRecord.none?
                String.format("用%s", procedure.attacker.weapon().name()): "";

        String result = String.format("%s%s%s攻击了%s%s, %s受到了%d点伤害, %s剩余生命: %d",
                procedure.attacker.role(), procedure.attacker.name(), weaponPart,
                procedure.attackable.role(), procedure.attackable.name(),
                procedure.attackable.name(), procedure.damage.extent,
                procedure.attackable.name(), procedure.attackable.health());

        return result;
    }

    public String formatOver(OverProcedure procedure) {
        String result = String.format("%s被打败了.", procedure.loser.name());

        return result;
    }

    public String formatEffect(EffectProcedure procedure) {
        return null;
    }
}
