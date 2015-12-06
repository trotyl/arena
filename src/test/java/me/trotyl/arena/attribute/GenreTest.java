package me.trotyl.arena.attribute;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class GenreTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void toString_should_have_proper_result() {

        assertThat(Genre.none.toString(), is("None"));
    }
}
