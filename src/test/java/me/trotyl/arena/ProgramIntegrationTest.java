package me.trotyl.arena;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;


public class ProgramIntegrationTest {

    private Program program;
    InputStream in;
    PrintStream out;

    @Before
    public void setUp() throws Exception {
        in = new FileInputStream("./fixture/config.json");

        out = mock(PrintStream.class);

        program = new Program(in, out);
    }

    @After
    public void tearDown() throws Exception {
        in.close();
    }

    @Test
    public void should_print_the_final_result() throws Exception {
        program.run();

        InOrder inOrder = inOrder(out);
        inOrder.verify(out).println("张三攻击了李四, 李四受到了8点伤害, 李四剩余生命: 12");
        inOrder.verify(out).println("李四攻击了张三, 张三受到了9点伤害, 张三剩余生命: 1");
        inOrder.verify(out).println("张三攻击了李四, 李四受到了8点伤害, 李四剩余生命: 4");
        inOrder.verify(out).println("李四攻击了张三, 张三受到了9点伤害, 张三剩余生命: -8");
        inOrder.verify(out).println("张三被打败了.");
        verifyNoMoreInteractions(out);
    }
}