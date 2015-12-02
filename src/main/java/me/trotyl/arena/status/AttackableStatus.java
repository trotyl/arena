package me.trotyl.arena.status;


public interface AttackableStatus extends Status, ArmorerStatus, RolerStatus {
    int health();
}
