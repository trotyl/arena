package me.trotyl.arena.equipment;

import me.trotyl.arena.record.ArmorRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ArmorTest {

    private Armor armor;

    @Before
    public void setUp() throws Exception {
        armor = Armor.create(5);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void create_should_have_proper_result_when_valid() {
        assertThat(Armor.create(5), instanceOf(Armor.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_should_have_proper_result_when_invalid() {
        Armor.create(-1);
    }

    @Test
    public void defence_should_have_proper_result() {
        assertThat(armor.getDefence(), is(5));
    }

    @Test
    public void record_should_have_proper_result() {

        ArmorRecord record = armor.record();

        assertThat(record.defence, is(5));
    }
}
