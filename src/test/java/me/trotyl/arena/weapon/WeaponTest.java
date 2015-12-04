package me.trotyl.arena.weapon;

import me.trotyl.arena.record.WeaponRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class WeaponTest {

    private Weapon weapon;

    @Before
    public void setUp() throws Exception {
        weapon = Weapon.create("优质木棒", 5, Length.medium);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void record_should_have_proper_result() {
        WeaponRecord record = weapon.record();

        assertThat(record.name(), is("优质木棒"));
    }
}