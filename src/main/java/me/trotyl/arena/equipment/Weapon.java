package me.trotyl.arena.equipment;


import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.record.WeaponRecord;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public abstract class Weapon implements Aggressive {

    public static final Weapon none = new Weapon("None", 0, Attribute.normalAttack) {

        @Override
        public Length getLength() {
            return Length.none;
        }
    };

    private final int aggressivity;
    private final String name;
    protected AggressiveAttribute attribute;

    protected Weapon(String name, int aggressivity, AggressiveAttribute attribute) {

        this.name = name;
        this.aggressivity = aggressivity;
        this.attribute = attribute;
    }

    public AggressiveAttribute getAttribute() {
        return attribute;
    }

    public int getAggressivity() {
        return aggressivity;
    }

    public abstract Length getLength();

    public String getName() {
        return name;
    }

    public WeaponRecord record() {

        if (this.equals(none)) {
            return WeaponRecord.none;
        }

        return WeaponRecord.create(name);
    }

    public void raise(AggressiveAttribute... attributes) {
        this.attribute = AggressiveAttribute.compose(this.attribute, stream(attributes).collect(toList()));
    }
}
