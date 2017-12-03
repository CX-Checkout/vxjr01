package befaster.solutions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class CheckoutTest {

    private static final String A_SKU = "A";
    private static final String B_SKU = "B";
    private static final String C_SKU = "C";
    private static final String D_SKU = "D";
    private static final String E_SKU = "E";
    private static final String F_SKU = "F";
    private static final int A_SKU_PRICE = 50;
    private static final int B_SKU_PRICE = 30;
    private static final int C_SKU_PRICE = 20;
    private static final int D_SKU_PRICE = 15;
    private static final int E_SKU_PRICE = 40;
    private static final int F_SKU_PRICE = 10;

    @Test
    public void should_return_price_of_each_sku_when_only_one_item_is_in_basket() {
        assertThat(Checkout.checkout(A_SKU), is(A_SKU_PRICE));
        assertThat(Checkout.checkout(B_SKU), is(B_SKU_PRICE));
        assertThat(Checkout.checkout(C_SKU), is(C_SKU_PRICE));
        assertThat(Checkout.checkout(D_SKU), is(D_SKU_PRICE));
        assertThat(Checkout.checkout(E_SKU), is(E_SKU_PRICE));
        assertThat(Checkout.checkout(F_SKU), is(F_SKU_PRICE));
    }

    @Test
    public void should_return_sum_of_prices_for_given_skus() {
        String allSkusOnceInBasket = "ABCDEF";
        String unorderedSkusInBasket = "BAFDADCE";
        int totalCheckoutValueForAllSkusOnceInBasket = A_SKU_PRICE + B_SKU_PRICE + C_SKU_PRICE + D_SKU_PRICE + E_SKU_PRICE + F_SKU_PRICE;
        int totalCheckoutValueForUnorderedBasket = 2 * A_SKU_PRICE + B_SKU_PRICE + C_SKU_PRICE + 2 * D_SKU_PRICE + E_SKU_PRICE + F_SKU_PRICE;

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
        int totalCheckoutValueForDiscountedMultipleTimesBasketWithOtherSkus = 5 * B_SKU_PRICE + 5 * D_SKU_PRICE - 2 * discountForDoubleBSkuInBasket;

        assertThat(Checkout.checkout(skusWithDiscountForDoubleB), is(totalCheckoutValueForBasket));
        assertThat(Checkout.checkout(skusWithDiscountForDoubleBMultipleTimes),
                is(totalCheckoutValueForDiscountedMultipleTimesBasket));
        assertThat(Checkout.checkout(skusWithDiscountForDoubleBWithOtherSkus),
                is(totalCheckoutValueForDiscountedMultipleTimesBasketWithOtherSkus));
    }

    @Test
    public void should_return_sum_of_prices_for_given_skus_including_discounts_for_each_triple_A_and_each_five_As_SKU_in_basket() {
        int discountForTripleASkuInBasket = 20;
        int discountForFiveAsSkuInBasket = 50;
        String skusWithDiscountForTripleA = "AAA";
        int totalCheckoutValueForBasket = 3 * A_SKU_PRICE - discountForTripleASkuInBasket;
        String skusWithDiscountForFiveAs = "AAAAA";
        int totalCheckoutValueForFiveAs = 5 * A_SKU_PRICE - discountForFiveAsSkuInBasket;
        String skusWithDiscountForFiveAsMultipleTimes = "AAAAAAAAAA";
        int totalCheckoutValueForFiveAsMultipleTimes = 10 * A_SKU_PRICE - 2 * discountForFiveAsSkuInBasket;
        String skusWithDiscountForFiveAsAndTripleA = "AAAAAAAAAAAAAA";
        int totalCheckoutValueForFiveAsAndTripleA = 14 * A_SKU_PRICE - 2 * discountForFiveAsSkuInBasket - discountForTripleASkuInBasket;
        String skusWithMixedDiscounts = "ADADADADADAD";
        int totalCheckoutValueForMixedDiscounts = 6 * A_SKU_PRICE + 6 * D_SKU_PRICE - discountForFiveAsSkuInBasket;

        assertThat(Checkout.checkout(skusWithDiscountForTripleA), is(totalCheckoutValueForBasket));
        assertThat(Checkout.checkout(skusWithDiscountForFiveAs), is(totalCheckoutValueForFiveAs));
        assertThat(Checkout.checkout(skusWithDiscountForFiveAsMultipleTimes), is(totalCheckoutValueForFiveAsMultipleTimes));
        assertThat(Checkout.checkout(skusWithMixedDiscounts), is(totalCheckoutValueForMixedDiscounts));
        assertThat(Checkout.checkout(skusWithDiscountForFiveAsAndTripleA), is(totalCheckoutValueForFiveAsAndTripleA));
    }

    @Test
    public void should_return_sum_of_prices_for_given_skus_including_one_free_B_sku_for_each_double_E_in_basket() {
        int discountForDoubleBSkuInBasket = 15;
        String skusWithDoubleEAndSingleB = "EBE";
        int totalCheckoutValueForDoubleEAndSingleB = 2 * E_SKU_PRICE;
        String skusWithDoubleEAndMultipleB = "EBEBB";
        int totalCheckoutValueForDoubleEAndMultipleB = 2 * E_SKU_PRICE + 2 * B_SKU_PRICE - discountForDoubleBSkuInBasket;
        String skusWithoutBAndDoubleEs = "EEEE";
        int totalCheckoutValueForBasketWithoutBAndDoublesEs = 4 * E_SKU_PRICE;
        String skusWithAllBsDiscounted = "EEEEBB";
        int totalCheckoutValueForAllBsDiscounted = 4 * E_SKU_PRICE;

        assertThat(Checkout.checkout(skusWithDoubleEAndSingleB), is(totalCheckoutValueForDoubleEAndSingleB));
        assertThat(Checkout.checkout(skusWithDoubleEAndMultipleB), is(totalCheckoutValueForDoubleEAndMultipleB));
        assertThat(Checkout.checkout(skusWithoutBAndDoubleEs), is(totalCheckoutValueForBasketWithoutBAndDoublesEs));
        assertThat(Checkout.checkout(skusWithAllBsDiscounted), is(totalCheckoutValueForAllBsDiscounted));
    }

    @Test
    public void should_return_sum_of_prices_for_given_skus_including_one_free_F_for_each_3_F_SKUs_in_basket() {
        String skusWithThreeFs = "FFF";
        int totalCheckoutValueForThreeFs = 2 * F_SKU_PRICE;
        String skusWithSixFs = "FFFFFF";
        int totalCheckoutValueForSixFs = 4 * F_SKU_PRICE;

        assertThat(Checkout.checkout(skusWithThreeFs), is(totalCheckoutValueForThreeFs));
        assertThat(Checkout.checkout(skusWithSixFs), is(totalCheckoutValueForSixFs));
    }

    @Test
    public void should_return_minus_one_for_skus_containing_illegal_elements() {
        String invalidSku = "V";
        String skusWithInvalidItem = "AVCD";
        String noSkusWithSpaces = " ";

        assertThat(Checkout.checkout(invalidSku), is(-1));
        assertThat(Checkout.checkout(skusWithInvalidItem), is(-1));
        assertThat(Checkout.checkout(noSkusWithSpaces), is(-1));
    }

    @Test
    public void should_return_zero_for_empty_basket() {
        String noSkus = "";

        assertThat(Checkout.checkout(noSkus), is(0));
    }

}
