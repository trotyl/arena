package me.trotyl.arena;


public class Game {
    private Player player1;
    private Player player2;
    private boolean inTurnOfPlayer1;

    public Game() {
        init();
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
        init();
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
        init();
    }

    public Player run() {
        while (player1.isAlive() && player2.isAlive()) {
            Player attacker = inTurnOfPlayer1? player1: player2;
            Player defender = inTurnOfPlayer1? player2: player1;

            attacker.attack(defender);
        }
        return player1.isAlive()? player2: player1;
    }

    private void init() {
        inTurnOfPlayer1 = true;
    }
}
