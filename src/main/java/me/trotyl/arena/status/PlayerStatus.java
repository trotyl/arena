package me.trotyl.arena.status;


import me.trotyl.arena.player.Player;
import me.trotyl.arena.player.Soldier;

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

    public String weapon() {
        if(!(player instanceof Soldier)) {
            return "";
        }
        Soldier soldier = (Soldier)player;
        if(soldier.weapon() == null) {
            return "";
        }
        return soldier.weapon().name();
    }

    public String role() {
        return (player instanceof Soldier)? "战士": "普通人";
    }
}
