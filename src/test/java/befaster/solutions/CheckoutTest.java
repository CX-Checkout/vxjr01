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
    private static final String G_SKU = "G";
    private static final String H_SKU = "H";
    private static final String I_SKU = "I";
    private static final String J_SKU = "J";
    private static final String K_SKU = "K";
    private static final String L_SKU = "L";
    private static final String M_SKU = "M";
    private static final String N_SKU = "N";
    private static final String O_SKU = "O";
    private static final String P_SKU = "P";
    private static final String Q_SKU = "Q";
    private static final String R_SKU = "R";
    private static final String S_SKU = "S";
    private static final String T_SKU = "T";
    private static final String U_SKU = "U";
    private static final String V_SKU = "V";
    private static final String W_SKU = "W";
    private static final String X_SKU = "X";
    private static final String Y_SKU = "Y";
    private static final String Z_SKU = "Z";
    private static final int A_SKU_PRICE = 50;
    private static final int B_SKU_PRICE = 30;
    private static final int C_SKU_PRICE = 20;
    private static final int D_SKU_PRICE = 15;
    private static final int E_SKU_PRICE = 40;
    private static final int F_SKU_PRICE = 10;
    private static final int G_SKU_PRICE = 20;
    private static final int H_SKU_PRICE = 10;
    private static final int I_SKU_PRICE = 35;
    private static final int J_SKU_PRICE = 60;
    private static final int K_SKU_PRICE = 80;
    private static final int L_SKU_PRICE = 90;
    private static final int M_SKU_PRICE = 15;
    private static final int N_SKU_PRICE = 40;
    private static final int O_SKU_PRICE = 10;
    private static final int P_SKU_PRICE = 50;
    private static final int Q_SKU_PRICE = 30;
    private static final int R_SKU_PRICE = 50;
    private static final int S_SKU_PRICE = 30;
    private static final int T_SKU_PRICE = 20;
    private static final int U_SKU_PRICE = 40;
    private static final int V_SKU_PRICE = 50;
    private static final int W_SKU_PRICE = 20;
    private static final int X_SKU_PRICE = 90;
    private static final int Y_SKU_PRICE = 10;
    private static final int Z_SKU_PRICE = 50;

    @Test
    public void should_return_price_of_each_sku_when_only_one_item_is_in_basket() {
        assertThat(Checkout.checkout(A_SKU), is(A_SKU_PRICE));
        assertThat(Checkout.checkout(B_SKU), is(B_SKU_PRICE));
        assertThat(Checkout.checkout(C_SKU), is(C_SKU_PRICE));
        assertThat(Checkout.checkout(D_SKU), is(D_SKU_PRICE));
        assertThat(Checkout.checkout(E_SKU), is(E_SKU_PRICE));
        assertThat(Checkout.checkout(F_SKU), is(F_SKU_PRICE));
        assertThat(Checkout.checkout(G_SKU), is(G_SKU_PRICE));
        assertThat(Checkout.checkout(H_SKU), is(H_SKU_PRICE));
        assertThat(Checkout.checkout(I_SKU), is(I_SKU_PRICE));
        assertThat(Checkout.checkout(J_SKU), is(J_SKU_PRICE));
        assertThat(Checkout.checkout(K_SKU), is(K_SKU_PRICE));
        assertThat(Checkout.checkout(L_SKU), is(L_SKU_PRICE));
        assertThat(Checkout.checkout(M_SKU), is(M_SKU_PRICE));
        assertThat(Checkout.checkout(N_SKU), is(N_SKU_PRICE));
        assertThat(Checkout.checkout(O_SKU), is(O_SKU_PRICE));
        assertThat(Checkout.checkout(P_SKU), is(P_SKU_PRICE));
        assertThat(Checkout.checkout(Q_SKU), is(Q_SKU_PRICE));
        assertThat(Checkout.checkout(R_SKU), is(R_SKU_PRICE));
        assertThat(Checkout.checkout(S_SKU), is(S_SKU_PRICE));
        assertThat(Checkout.checkout(T_SKU), is(T_SKU_PRICE));
        assertThat(Checkout.checkout(U_SKU), is(U_SKU_PRICE));
        assertThat(Checkout.checkout(V_SKU), is(V_SKU_PRICE));
        assertThat(Checkout.checkout(W_SKU), is(W_SKU_PRICE));
        assertThat(Checkout.checkout(X_SKU), is(X_SKU_PRICE));
        assertThat(Checkout.checkout(Y_SKU), is(Y_SKU_PRICE));
        assertThat(Checkout.checkout(Z_SKU), is(Z_SKU_PRICE));
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
    public void should_return_sum_of_prices_for_given_skus_including_discounts_for_each_double_K_SKU_in_basket() {
        int discountForDoubleKSkuInBasket = 10;
        String skusWithDiscountForDoubleK = "KK";
        int totalCheckoutValueForBasket = 2 * K_SKU_PRICE - discountForDoubleKSkuInBasket;
        String skusWithDiscountForDoubleKMultipleTimes = "KKKKKKK";
        int totalCheckoutValueForDiscountedMultipleTimesBasket = 7 * K_SKU_PRICE - 3 * discountForDoubleKSkuInBasket;
        String skusWithDiscountForDoubleKWithOtherSkus = "DKDKDKDKDK";
        int totalCheckoutValueForDiscountedMultipleTimesBasketWithOtherSkus = 5 * K_SKU_PRICE + 5 * D_SKU_PRICE - 2 * discountForDoubleKSkuInBasket;

        assertThat(Checkout.checkout(skusWithDiscountForDoubleK), is(totalCheckoutValueForBasket));
        assertThat(Checkout.checkout(skusWithDiscountForDoubleKMultipleTimes),
                is(totalCheckoutValueForDiscountedMultipleTimesBasket));
        assertThat(Checkout.checkout(skusWithDiscountForDoubleKWithOtherSkus),
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
        assertThat(Checkout.checkout(skusWithDiscountForFiveAsAndTripleA), is(totalCheckoutValueForFiveAsAndTripleA));
        assertThat(Checkout.checkout(skusWithMixedDiscounts), is(totalCheckoutValueForMixedDiscounts));
    }

    @Test
    public void should_return_sum_of_prices_for_given_skus_including_discounts_for_each_five_A_and_each_ten_H_SKUs_in_basket() {
        int discountForFiveHsSkuInBasket = 5;
        int discountForTenHsSkuInBasket = 20;
        String skusWithDiscountForFiveHs = "HHHHH";
        int totalCheckoutValueForBasket = 5 * H_SKU_PRICE - discountForFiveHsSkuInBasket;
        String skusWithDiscountForTenHs = "HHHHHHHHHH";
        int totalCheckoutValueForTenHs = 10 * H_SKU_PRICE - discountForTenHsSkuInBasket;
        String skusWithDiscountForTenHsMultipleTimes = "HHHHHHHHHHHHHHHHHHHH";
        int totalCheckoutValueForTenHsMultipleTimes = 20 * H_SKU_PRICE - 2 * discountForTenHsSkuInBasket;
        String skusWithDiscountForTenHsAndFiveHs = "HHHHHHHHHHHHHHHHHHHHHHHHH";
        int totalCheckoutValueForTenHsAndFiveHs = 25 * H_SKU_PRICE - 2 * discountForTenHsSkuInBasket - discountForFiveHsSkuInBasket;
        String skusWithMixedDiscounts = "HDHDHDHDHDHD";
        int totalCheckoutValueForMixedDiscounts = 6 * H_SKU_PRICE + 6 * D_SKU_PRICE - discountForFiveHsSkuInBasket;

        assertThat(Checkout.checkout(skusWithDiscountForFiveHs), is(totalCheckoutValueForBasket));
        assertThat(Checkout.checkout(skusWithDiscountForTenHs), is(totalCheckoutValueForTenHs));
        assertThat(Checkout.checkout(skusWithDiscountForTenHsMultipleTimes), is(totalCheckoutValueForTenHsMultipleTimes));
        assertThat(Checkout.checkout(skusWithDiscountForTenHsAndFiveHs), is(totalCheckoutValueForTenHsAndFiveHs));
        assertThat(Checkout.checkout(skusWithMixedDiscounts), is(totalCheckoutValueForMixedDiscounts));
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
    public void should_return_sum_of_prices_for_given_skus_including_one_free_M_sku_for_each_double_E_in_basket() {
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
        String invalidSku = "!";
        String skusWithInvalidItem = "A!CD";
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
