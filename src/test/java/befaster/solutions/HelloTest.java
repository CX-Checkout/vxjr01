package befaster.solutions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class HelloTest {

    @Test
    public void should_return_hello_world() {
        assertThat(Hello.hello("Robert"), is("Hello, World!"));
        assertThat(Hello.hello("Mirek"), is("Hello, World!"));
    }

    @Test
    public void should_return_hello_world_when_name_is_undefined() {
        assertThat(Hello.hello(""), is("Hello, World!"));
        assertThat(Hello.hello(null), is("Hello, World!"));
    }

}