package me.trotyl.arena.attribute;


import me.trotyl.arena.effect.Effect;
import me.trotyl.arena.record.DamageRecord;
import me.trotyl.arena.role.Player;

import java.util.List;

import static java.util.Arrays.asList;

public abstract class AggressiveAttribute extends Attribute {

    public static AggressiveAttribute compose(AggressiveAttribute attribute, List<AggressiveAttribute> attributes) {

        AggressiveAttribute composite = compose(attributes);

        if (attribute.equals(Attribute.normalAttack)) {
            return composite;
        } else if (composite.equals(Attribute.normalAttack)) {
            return attribute;
        } else {
            List<AggressiveAttribute> list = asList(attribute, composite);
            return compose(list);
        }
    }

    public static AggressiveAttribute compose(List<AggressiveAttribute> attributes) {

        if (attributes.size() == 0) {
            return Attribute.normalAttack;
        } else if (attributes.size() == 1) {
            return attributes.get(0);
        }

        AggressiveAttribute composite = compose(attributes.subList(1, attributes.size()));

        return new CompositeAttribute(attributes.get(0), composite);
    }

    protected AggressiveAttribute(int limit, float rate) {
        super(limit, rate);
    }


    public int getLimit() {
        return limit;
    }

    public float getRate() {
        return rate;
    }

    public DamageRecord apply(Player attacker, Player defender, AggressiveAttribute next) {

        if (!works()) {
            return next.apply(attacker, defender, AggressiveAttribute.normalAttack);
        }

        int damage = getDamage(attacker, defender);
        DamageRecord damageRecord = DamageRecord.create(damage, getGenre());

        DefensiveAttribute defensiveAttribute = defender.getDefensiveAttribute();

        return defensiveAttribute.apply(damageRecord, getEffect(), attacker, defender);
    }

    protected int getDamage(Player attacker, Player defender) {
        return attacker.getAggressivity() - defender.getDefence();
    }

    protected Effect getEffect() {
        return Effect.none;
    }

    protected Genre getGenre() {
        return Genre.none;
    }

    protected boolean works() {
        return random.nextFloat() < rate;
    }
}
