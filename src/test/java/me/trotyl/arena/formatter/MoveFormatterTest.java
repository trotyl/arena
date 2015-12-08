package me.trotyl.arena.formatter;

import me.trotyl.arena.procedure.MoveProcedure;
import me.trotyl.arena.record.PlayerRecord;
import me.trotyl.arena.role.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MoveFormatterTest {

    private MoveFormatter formatter;

    @Before
    public void setUp() throws Exception {
        formatter = new MoveFormatter();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void format_should_have_proper_result() {

        MoveProcedure procedure = MoveProcedure.create(2,
                PlayerRecord.create("张三", 10, Role.fighter),
                PlayerRecord.create("李四", 20, Role.normal));

        String result = formatter.format(procedure);

        assertThat(result, is("张三靠近了李四"));
    }

    @Test
    public void format_should_have_proper_result_when_none() {

        MoveProcedure procedure = MoveProcedure.none;

        String result = formatter.format(procedure);

        assertThat(result, is((String)null));
    }
}
