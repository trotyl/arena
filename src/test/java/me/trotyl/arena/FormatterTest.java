package me.trotyl.arena;

import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.effect.Type;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.record.*;
import me.trotyl.arena.role.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FormatterTest {

    private Formatter formatter;

    @Before
    public void setUp() throws Exception {
        formatter = new Formatter();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void format_attack_should_have_proper_result_without_weapon_nor_attribute() {

        AttackProcedure procedure = AttackProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                                                           PlayerRecord.create("李四", 20, Role.normal),
                                                           DamageRecord.create(5));

        String result = formatter.formatAttack(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四剩余生命: 20"));
    }

    @Test
    public void format_attack_should_have_proper_result_with_weapon() {

        AttackProcedure procedure = AttackProcedure.create(
                PlayerRecord.create("张三", 10, Role.fighter, new WeaponRecord("优质木棒"), ArmorRecord.none),
                PlayerRecord.create("李四", 20, Role.normal),
                DamageRecord.create(5));

        String result = formatter.formatAttack(procedure);

        assertThat(result, is("战士张三用优质木棒攻击了普通人李四, 李四受到了5点伤害, 李四剩余生命: 20"));
    }

    @Test
    public void format_attack_should_have_proper_result_with_normal_effect() {

        AttackProcedure procedure = AttackProcedure.create(
                PlayerRecord.create("张三", 10, Role.fighter),
                PlayerRecord.create("李四", 20, Role.normal),
                DamageRecord.create(5, Genre.toxic));

        String result = formatter.formatAttack(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四中毒了, 李四剩余生命: 20"));
    }

    @Test
    public void format_attack_should_have_proper_result_with_striking() {

        AttackProcedure procedure = AttackProcedure.create(
                PlayerRecord.create("张三", 10, Role.fighter),
                PlayerRecord.create("李四", 20, Role.normal),
                DamageRecord.create(5, Genre.striking));

        String result = formatter.formatAttack(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 张三发动了全力一击, 李四受到了5点伤害, 李四剩余生命: 20"));
    }


    @Test
    public void format_attack_should_have_proper_result_with_weapon_and_effect() {

        AttackProcedure procedure = AttackProcedure.create(
                PlayerRecord.create("张三", 10, Role.fighter, new WeaponRecord("优质木棒"), ArmorRecord.none),
                PlayerRecord.create("李四", 20, Role.normal),
                DamageRecord.create(5, Genre.toxic));

        String result = formatter.formatAttack(procedure);

        assertThat(result, is("战士张三用优质木棒攻击了普通人李四, 李四受到了5点伤害, 李四中毒了, 李四剩余生命: 20"));
    }

    @Test
    public void format_over_should_have_proper_result() {

        OverProcedure procedure = OverProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                                                       PlayerRecord.create("李四", 20, Role.normal));

        String result = formatter.formatOver(procedure);

        assertThat(result, is("李四被打败了."));
    }

    @Test
    public void format_effect_should_have_proper_result_for_toxin() {

        EffectProcedure procedure = EffectProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                                                           EffectRecord.create(Type.toxin),
                                                           DamageRecord.create(5));

        String result = formatter.formatEffect(procedure);

        assertThat(result, is("张三受到5点毒性伤害, 张三剩余生命: 10"));
    }

    @Test
    public void format_effect_should_have_proper_result_for_fire() {

        EffectProcedure procedure = EffectProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                                                           EffectRecord.create(Type.fire),
                                                           DamageRecord.create(5));

        String result = formatter.formatEffect(procedure);

        assertThat(result, is("张三受到5点火焰伤害, 张三剩余生命: 10"));
    }

    @Test
    public void format_effect_should_have_proper_result_for_freeze() {

        EffectProcedure procedure = EffectProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                                                           EffectRecord.create(Type.freeze, 1),
                                                           DamageRecord.create(5));

        String result = formatter.formatEffect(procedure);

        assertThat(result, is("张三冻僵了, 无法攻击."));
    }

    @Test
    public void format_effect_should_have_proper_result_for_swoon() {

        EffectProcedure procedure = EffectProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                                                           EffectRecord.create(Type.swoon, 2),
                                                           DamageRecord.create(5));

        String result = formatter.formatEffect(procedure);

        assertThat(result, is("张三晕倒了, 无法攻击, 眩晕还剩: 2轮"));
    }
}