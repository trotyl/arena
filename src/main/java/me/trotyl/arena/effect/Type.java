package me.trotyl.arena.effect;


public class Type {
    public static final Type toxin = new Type();
    public static final Type none = new Type();

    @Override
    public String toString() {
        if (this.equals(toxin)) {
            return "毒性";
        }
        return "";
    }
}
