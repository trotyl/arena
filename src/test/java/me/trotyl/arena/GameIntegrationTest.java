package me.trotyl.arena;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class GameIntegrationTest {

    private Game game;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() throws Exception {
        player1 = new Player("张三", 10, 5);
        player2 = new Player("李四", 8, 2);

        game = new Game();
        game.setPlayer1(player1);
        game.setPlayer2(player2);
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void should_return_the_failed_player() throws Exception {
        Player loser = game.run();

        assertThat(loser.getName(), is("李四"));
    }
}