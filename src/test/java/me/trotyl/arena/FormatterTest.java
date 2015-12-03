package me.trotyl.arena;

import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.record.ArmorRecord;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.record.WeaponRecord;
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
        AttackProcedure procedure = new AttackProcedure(
                new PlayerRecord("张三", 10, Role.soldier),
                new PlayerRecord("李四", 20, Role.normal),
                new DamageRecord(5));

        String result = formatter.formatAttack(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四剩余生命: 20"));
    }

    @Test
    public void format_attack_should_have_proper_result_with_weapon() {
        AttackProcedure procedure = new AttackProcedure(
                new PlayerRecord("张三", 10, Role.soldier, new WeaponRecord("优质木棒"), ArmorRecord.none),
                new PlayerRecord("李四", 20, Role.normal),
                new DamageRecord(5));

        String result = formatter.formatAttack(procedure);

        assertThat(result, is("战士张三用优质木棒攻击了普通人李四, 李四受到了5点伤害, 李四剩余生命: 20"));
    }

    @Test
    public void format_attack_should_have_proper_result_with_effect() {
        AttackProcedure procedure = new AttackProcedure(
                new PlayerRecord("张三", 10, Role.soldier),
                new PlayerRecord("李四", 20, Role.normal),
                new DamageRecord(Genre.toxic, 5));

        String result = formatter.formatAttack(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四中毒了, 李四剩余生命: 20"));
    }

    @Test
    public void format_attack_should_have_proper_result_with_weapon_and_effect() {
        AttackProcedure procedure = new AttackProcedure(
                new PlayerRecord("张三", 10, Role.soldier, new WeaponRecord("优质木棒"), ArmorRecord.none),
                new PlayerRecord("李四", 20, Role.normal),
                new DamageRecord(Genre.toxic, 5));

        String result = formatter.formatAttack(procedure);

        assertThat(result, is("战士张三用优质木棒攻击了普通人李四, 李四受到了5点伤害, 李四中毒了, 李四剩余生命: 20"));
    }
}