package me.trotyl.arena;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GameUnitTest {
    private Game game;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() throws Exception {
        player1 = mock(Player.class);
        when(player1.getName()).thenReturn("张三");


        player2 = mock(Player.class);
        when(player2.getName()).thenReturn("李四");

        game = new Game();
        game.setPlayer1(player1);
        game.setPlayer2(player2);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_return_the_failed_player() throws Exception {
        when(player1.isAlive()).thenReturn(true);
        when(player2.isAlive()).thenReturn(true, true, false);

        Player loser = game.run();

        assertThat(loser.getName(), is("李四"));
    }
}