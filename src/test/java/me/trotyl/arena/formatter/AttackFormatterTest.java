package me.trotyl.arena.formatter;

import me.trotyl.arena.attribute.Genre;
import me.trotyl.arena.procedure.AttackProcedure;
import me.trotyl.arena.record.*;
import me.trotyl.arena.role.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AttackFormatterTest {

    private AttackFormatter formatter;

    @Before
    public void setUp() throws Exception {
        formatter = new AttackFormatter();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void format_should_have_proper_result_without_weapon_nor_attribute() {

        AttackProcedure procedure = AttackProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                                                           PlayerRecord.create("李四", 20, Role.normal),
                                                           DamageRecord.create(5));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四剩余生命: 20"));
    }

    @Test
    public void format_should_have_proper_result_with_weapon() {

        AttackProcedure procedure = AttackProcedure.create(
                PlayerRecord.create("张三", 10, Role.fighter, WeaponRecord.create("优质木棒"), ArmorRecord.none),
                PlayerRecord.create("李四", 20, Role.normal),
                DamageRecord.create(5));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三用优质木棒攻击了普通人李四, 李四受到了5点伤害, 李四剩余生命: 20"));
    }

    @Test
    public void format_should_have_proper_result_with_toxic() {

        AttackProcedure procedure = AttackProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                                                           PlayerRecord.create("李四", 20, Role.normal),
                                                           DamageRecord.create(5, Genre.toxic));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四中毒了, 李四剩余生命: 20"));
    }

    @Test
    public void format_should_have_proper_result_with_flaming() {

        AttackProcedure procedure = AttackProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                PlayerRecord.create("李四", 20, Role.normal),
                DamageRecord.create(5, Genre.flaming));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四着火了, 李四剩余生命: 20"));
    }

    @Test
    public void format_should_have_proper_result_with_freezing() {

        AttackProcedure procedure = AttackProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                PlayerRecord.create("李四", 20, Role.normal),
                DamageRecord.create(5, Genre.freezing));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四冻僵了, 李四剩余生命: 20"));
    }

    @Test
    public void format_should_have_proper_result_with_dizzy() {

        AttackProcedure procedure = AttackProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                PlayerRecord.create("李四", 20, Role.normal),
                DamageRecord.create(5, Genre.dizzy));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四晕倒了, 李四剩余生命: 20"));
    }

    @Test
    public void format_should_have_proper_result_with_striking() {

        AttackProcedure procedure = AttackProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                                                           PlayerRecord.create("李四", 20, Role.normal),
                                                           DamageRecord.create(5, Genre.striking));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 张三发动了全力一击, 李四受到了5点伤害, 李四剩余生命: 20"));
    }

    @Test
    public void format_should_have_proper_result_with_repel() {

        AttackProcedure procedure = AttackProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                PlayerRecord.create("李四", 20, Role.normal),
                RepelDamageRecord.create(2, DamageRecord.create(5)));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四被击退了, 李四剩余生命: 20"));
    }

    @Test
    public void format_should_have_proper_result_with_repel_and_toxic() {

        AttackProcedure procedure = AttackProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                PlayerRecord.create("李四", 20, Role.normal),
                RepelDamageRecord.create(2, DamageRecord.create(5, Genre.toxic)));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四中毒了, 李四被击退了, 李四剩余生命: 20"));
    }

    @Test
    public void format_should_have_proper_result_with_repel_and_striking() {

        AttackProcedure procedure = AttackProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                PlayerRecord.create("李四", 20, Role.normal),
                RepelDamageRecord.create(2, DamageRecord.create(15, Genre.striking)));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 张三发动了全力一击, 李四受到了15点伤害, 李四被击退了, 李四剩余生命: 20"));
    }

    @Test
    public void format_should_have_proper_result_with_carom() {

        AttackProcedure procedure = AttackProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                PlayerRecord.create("李四", 20, Role.normal),
                CaromDamageRecord.create(DamageRecord.create(5), DamageRecord.create(5)));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 张三发动了连击, 李四受到了5点伤害, 李四剩余生命: 20"));
    }

    @Test
    public void format_should_have_proper_result_with_carom_and_toxic_then_toxic() {

        AttackProcedure procedure = AttackProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                PlayerRecord.create("李四", 20, Role.normal),
                CaromDamageRecord.create(DamageRecord.create(5, Genre.toxic), DamageRecord.create(5, Genre.toxic)));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四中毒了, 张三发动了连击, 李四受到了5点伤害, 李四中毒了, 李四剩余生命: 20"));
    }

    @Test
    public void format_should_have_proper_result_with_carom_and_toxic_then_striking() {

        AttackProcedure procedure = AttackProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                PlayerRecord.create("李四", 20, Role.normal),
                CaromDamageRecord.create(DamageRecord.create(5, Genre.toxic), DamageRecord.create(5, Genre.striking)));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四中毒了, 张三发动了连击, 张三发动了全力一击, 李四受到了5点伤害, 李四剩余生命: 20"));
    }

    @Test
    public void format_should_have_proper_result_with_counter() {

        AttackProcedure procedure = AttackProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                PlayerRecord.create("李四", 20, Role.normal),
                CounterDamageRecord.create(DamageRecord.create(5), DamageRecord.create(5)));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三攻击了普通人李四, 李四受到了5点伤害, 李四发动了格挡反击, 张三受到了5点伤害, 李四剩余生命: 20, 张三剩余生命: 10"));
    }

    @Test
    public void format_should_have_proper_result_with_weapon_and_effect() {

        AttackProcedure procedure = AttackProcedure.create(
                PlayerRecord.create("张三", 10, Role.fighter, WeaponRecord.create("优质木棒"), ArmorRecord.none),
                PlayerRecord.create("李四", 20, Role.normal),
                DamageRecord.create(5, Genre.toxic));

        String result = formatter.format(procedure);

        assertThat(result, is("战士张三用优质木棒攻击了普通人李四, 李四受到了5点伤害, 李四中毒了, 李四剩余生命: 20"));
    }
}
