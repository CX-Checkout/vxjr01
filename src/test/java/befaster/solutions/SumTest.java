package befaster.solutions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class SumTest {

    @Test
    public void should_compute_sum() {
        assertThat(Sum.sum(1, 1), equalTo(2));
    }
//
//    @Test
//    public void should_() {
//        assertThat(, is());
//    }
}