package me.trotyl.arena.equipment;


import me.trotyl.arena.attribute.AggressiveAttribute;
import me.trotyl.arena.attribute.Attribute;
import me.trotyl.arena.attribute.DefensiveAttribute;
import me.trotyl.arena.record.WeaponRecord;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public abstract class Weapon implements Aggressive {

    public static final Weapon none = new Weapon("None", 0, Attribute.normalAttack) {};

    private final int aggressivity;
    private final String name;
    protected AggressiveAttribute aggressiveAttribute;
    private DefensiveAttribute defensiveAttribute;

    protected Weapon(String name, int aggressivity, AggressiveAttribute attribute) {

        this.name = name;
        this.aggressivity = aggressivity;
        this.aggressiveAttribute = attribute;
        this.defensiveAttribute = Attribute.normalDefence;
    }

    public AggressiveAttribute getAggressiveAttribute() {
        return aggressiveAttribute;
    }

    public int getAggressivity() {
        return aggressivity;
    }

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
        this.aggressiveAttribute = AggressiveAttribute.compose(this.aggressiveAttribute, stream(attributes).collect(toList()));
    }

    public DefensiveAttribute getDefensiveAttribute() {
        return defensiveAttribute;
    }
}
