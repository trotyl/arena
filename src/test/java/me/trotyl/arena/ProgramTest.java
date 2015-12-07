package me.trotyl.arena;

import me.trotyl.arena.formatter.Formatter;
import me.trotyl.arena.parser.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;


public class ProgramTest {

    InputStream in;
    PrintStream out;

    @Before
    public void setUp() throws Exception {
        out = mock(PrintStream.class);
    }

    @After
    public void tearDown() throws Exception {
        in.close();
    }

    @Test
    public void run_should_have_proper_result() throws Exception {

        in = new FileInputStream("./fixture/config0.json");

        Program program = new Program(in, out, Parser.defaults(), Formatter.defaults());

        program.run();

        InOrder inOrder = inOrder(out);
        inOrder.verify(out).println("普通人张三攻击了普通人李四, 李四受到了5点伤害, 李四剩余生命: 15");
        inOrder.verify(out).println("普通人李四攻击了普通人张三, 张三受到了8点伤害, 张三剩余生命: 2");
        inOrder.verify(out).println("普通人张三攻击了普通人李四, 李四受到了5点伤害, 李四剩余生命: 10");
        inOrder.verify(out).println("普通人李四攻击了普通人张三, 张三受到了8点伤害, 张三剩余生命: -6");
        inOrder.verify(out).println("张三被打败了.");

        verifyNoMoreInteractions(out);
    }
}
