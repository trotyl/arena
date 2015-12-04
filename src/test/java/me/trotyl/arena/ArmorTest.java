package me.trotyl.arena;

import me.trotyl.arena.armor.Armor;
import me.trotyl.arena.record.ArmorRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void defence_should_have_proper_result() {
        assertThat(armor.defence(), is(5));
    }

    @Test
    public void record_should_have_proper_result() {

        ArmorRecord record = armor.record();

        assertThat(record.defence, is(5));
    }
}