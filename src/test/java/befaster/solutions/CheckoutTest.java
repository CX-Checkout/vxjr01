package befaster.solutions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class CheckoutTest {

    private static final String A_SKU = "A";
    private static final String B_SKU = "B";
    private static final String C_SKU = "C";
    private static final String D_SKU = "D";
    private static final int A_SKU_PRICE = 50;
    private static final int B_SKU_PRICE = 30;
    private static final int C_SKU_PRICE = 20;
    private static final int D_SKU_PRICE = 15;

    @Test
    public void should_return_price_of_each_sku_when_only_one_item_is_in_basket() {
        assertThat(Checkout.checkout(A_SKU), is(A_SKU_PRICE));
        assertThat(Checkout.checkout(B_SKU), is(B_SKU_PRICE));
        assertThat(Checkout.checkout(C_SKU), is(C_SKU_PRICE));
        assertThat(Checkout.checkout(D_SKU), is(D_SKU_PRICE));
    }

    @Test
    public void should_return_sum_of_prices_for_given_skus() {
        String allSkusOnceInBasket = "ABCD";
        String unorderedSkusInBasket = "BADADC";
        int totalCheckoutValueForAllSkusOnceInBasket = A_SKU_PRICE + B_SKU_PRICE + C_SKU_PRICE + D_SKU_PRICE;
        int totalCheckoutValueForUnorderedBasket = 2 * A_SKU_PRICE + B_SKU_PRICE + C_SKU_PRICE + 2 * D_SKU_PRICE;

        assertThat(Checkout.checkout(allSkusOnceInBasket), is(totalCheckoutValueForAllSkusOnceInBasket));
        assertThat(Checkout.checkout(unorderedSkusInBasket), is(totalCheckoutValueForUnorderedBasket));
    }

    @Test
    public void should_return_sum_of_prices_for_given_skus_including_discounts_for_each_double_B_SKU_in_basket() {
        String skusWithDiscountForDoubleB = "BB";
        String skusWithDiscountForDoubleBMultipleTimes = "BBBBBBB";
        String skusWithDiscountForDoubleBWithOtherSkus = "DBDBDBDBDB";
        int discountForDoubleBSkuInBasket = 15;
        int totalCheckoutValueForBasket = 2 * B_SKU_PRICE - discountForDoubleBSkuInBasket;
        int totalCheckoutValueForDiscountedMultipleTimesBasket = 7 * B_SKU_PRICE - 3 * discountForDoubleBSkuInBasket;
        int totalCheckoutValueForDiscountedMultipleTimesBasketWithOtherSkus = 5 * D_SKU_PRICE +  5 * B_SKU_PRICE - 2 * discountForDoubleBSkuInBasket;

        assertThat(Checkout.checkout(skusWithDiscountForDoubleB), is(totalCheckoutValueForBasket));
        assertThat(Checkout.checkout(skusWithDiscountForDoubleBMultipleTimes), is(totalCheckoutValueForDiscountedMultipleTimesBasket));
        assertThat(Checkout.checkout(skusWithDiscountForDoubleBWithOtherSkus), is(totalCheckoutValueForDiscountedMultipleTimesBasketWithOtherSkus));
    }
}
