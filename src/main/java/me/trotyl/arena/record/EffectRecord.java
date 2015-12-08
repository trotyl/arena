package me.trotyl.arena.record;


import me.trotyl.arena.attribute.Genre;

public class EffectRecord implements Record {

    public static final EffectRecord none = new EffectRecord(Genre.none, -1, Action.none);

    public static EffectRecord create(Genre genre, Action next) {
        return new EffectRecord(genre, -1, next);
    }

    public static EffectRecord create(Genre genre, int remain, Action next) {

        if (genre.equals(Genre.none)) {
            return none;
        }

        return new EffectRecord(genre, remain, next);
    }

    public Genre genre;
    public int remain;
    public Action next;

    protected EffectRecord(Genre genre, int remain, Action next) {

        this.genre = genre;
        this.remain = remain;
        this.next = next;
    }

    @Override
    public String getName() {
        return genre.toString();
    }
}
