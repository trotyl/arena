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
        inOrder.verify(out).println("李四被打败了.");
        verifyNoMoreInteractions(out);
    }
}