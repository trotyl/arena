package me.trotyl.arena;

import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.role.*;
import org.javatuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class FormatterTest {

    private Formatter formatter;
    private Player player, anotherPlayer;
    private Soldier soldierWithoutEquipment, soldierWithWeapon, soldierWithArmor, soldierWithWeaponAndArmor;
    private Soldier anotherSoldierWithoutEquipment, anotherSoldierWithWeapon,
            anotherSoldierWithArmor, anotherSoldierWithWeaponAndArmor;

    private String formattedAttack(Attacker attacker, Attackable attackable) {
        Pair<EffectProcedure, AttackProcedure> pair = attacker.attack(attackable);
        AttackProcedure attack = pair.getValue1();
        return formatter.formatAttack(attack);
    }

    private String formattedEffect(Attacker attacker, Attackable attackable) {
        Pair<EffectProcedure, AttackProcedure> pair = attacker.attack(attackable);
        EffectProcedure effect = pair.getValue0();
        return formatter.formatEffect(effect);
    }

    @Before
    public void setUp() throws Exception {
        formatter = new Formatter();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void format_attack_should_have_proper_result_without_weapon_nor_attribute() {
        AttackProcedure procedure = new AttackProcedure(new PlayerRecord("张三", 10, Role.soldier),
                new PlayerRecord("李四", 20, Role.normal),
                new DamageRecord(5));

        String result = formatter.formatAttack(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四剩余生命: 20"));
    }
}