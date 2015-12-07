package me.trotyl.arena.formatter;

import me.trotyl.arena.procedure.OverProcedure;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.role.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OverFormatterTest {

    private OverFormatter formatter;

    @Before
    public void setUp() throws Exception {
        formatter = new OverFormatter();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void format_should_have_proper_result() {

        OverProcedure procedure = OverProcedure.create(PlayerRecord.create("张三", 10, Role.fighter),
                PlayerRecord.create("李四", 20, Role.normal));

        String result = formatter.format(procedure);

        assertThat(result, is("李四被打败了."));
    }
}
