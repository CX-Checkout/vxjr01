package befaster.solutions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class CheckoutTest {

    @Test
    public void should_return_price_of_each_sku_when_only_one_item_is_given() {
        assertThat(Checkout.checkout("A"), is(50));
        assertThat(Checkout.checkout("B"), is(30));
        assertThat(Checkout.checkout("C"), is(20));
        assertThat(Checkout.checkout("D"), is(15));
    }



}