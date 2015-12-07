package me.trotyl.arena.formatter;

import me.trotyl.arena.effect.Type;
import me.trotyl.arena.procedure.EffectProcedure;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.record.EffectRecord;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.role.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EffectFormatterTest {

    private EffectFormatter formatter;

    @Before
    public void setUp() throws Exception {
        formatter = new EffectFormatter();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void format_should_have_proper_result_for_toxin() {

        EffectProcedure procedure = EffectProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                                                           PlayerRecord.create("李四", 8, Role.knight),
                                                           EffectRecord.create(Type.toxin),
                                                           DamageRecord.create(5));

        String result = formatter.format(procedure);

        assertThat(result, is("张三受到5点毒性伤害, 张三剩余生命: 10"));
    }

    @Test
    public void format_should_have_proper_result_for_flame() {

        EffectProcedure procedure = EffectProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                                                           PlayerRecord.create("李四", 8, Role.knight),
                                                           EffectRecord.create(Type.flame),
                                                           DamageRecord.create(5));

        String result = formatter.format(procedure);

        assertThat(result, is("张三受到5点火焰伤害, 张三剩余生命: 10"));
    }

    @Test
    public void format_should_have_proper_result_for_freeze() {

        EffectProcedure procedure = EffectProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                                                           PlayerRecord.create("李四", 8, Role.knight),
                                                           EffectRecord.create(Type.freeze, 1),
                                                           DamageRecord.create(5));

        String result = formatter.format(procedure);

        assertThat(result, is("张三冻得直哆嗦, 没有击中李四"));
    }

    @Test
    public void format_should_have_proper_result_for_swoon() {

        EffectProcedure procedure = EffectProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                                                           PlayerRecord.create("李四", 8, Role.knight),
                                                           EffectRecord.create(Type.swoon, 2),
                                                           DamageRecord.create(5));

        String result = formatter.format(procedure);

        assertThat(result, is("张三晕倒了, 无法攻击, 眩晕还剩: 1轮"));
    }
}
