package me.trotyl.arena.effect;


public class Type {
    public static final Type toxin = new Type();
    public static final Type none = new Type();
    public static final Type swoon = new Type();
    public static final Type flame = new Type();
    public static final Type freeze = new Type();

    @Override
    public String toString() {
        return this.equals(toxin)? "Toxin":
            this.equals(none)? "None":
            this.equals(swoon)? "Swoon":
            this.equals(flame)? "Flame":
            this.equals(freeze)? "Freeze": "None";
    }
}
