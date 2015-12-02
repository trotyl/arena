package me.trotyl.arena.status;


import me.trotyl.arena.Player;

public class PlayerStatus {
    private Player player;
    private int health;

    public PlayerStatus(Player player) {
        this.player = player;
        this.health = player.health();
    }

    public int health() {
        return health;
    }

    public String name() {
        return player.name();
    }
}
