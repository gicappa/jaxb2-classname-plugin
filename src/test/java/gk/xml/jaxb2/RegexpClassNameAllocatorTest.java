package gk.xml.jaxb2;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RegexpClassNameAllocatorTest {

    private RegexpClassNameAllocator allocator;

    @Before
    public void before() {
        allocator = new RegexpClassNameAllocator("-cn:/(.*)Type/$1/");
    }

    @Test
    public void when_regexp_param_is_passed_it_should_be_parsed() {
        assertThat(allocator.parseOption("-cn:/(.*)Type/$1/"), hasItems("(.*)Type", "$1"));
    }

    @Test
    public void when_regexp_param_is_passed_it_should_be_parsed2() {
        assertThat(allocator.parseOption("-cn:/(.*)Type/$1/").size(), is(3));
    }

    @Test
    public void allocator_substitues_the_name() {
        assertThat(allocator.assignClassName("pkg", "EntityType"), is("Entity"));
    }

    @Test
    public void allocator_substitues_the_name2() {
        assertThat(allocator.assignClassName("pkg", "EntityTypeType"), is("EntityType"));
    }

}
