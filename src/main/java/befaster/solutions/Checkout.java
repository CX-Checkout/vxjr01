package befaster.solutions;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import java.util.HashMap;
import java.util.List;

public class Checkout {
    private static final String A_SKU = "A";
    private static final String B_SKU = "B";
    private static final String C_SKU = "C";
    private static final String D_SKU = "D";
    private static final String E_SKU = "E";
    private static final String F_SKU = "F";

    private static final HashMap<String, Integer> priceMap = new HashMap<String, Integer>() {{
        put(A_SKU, 50);
        put(B_SKU, 30);
        put(C_SKU, 20);
        put(D_SKU, 15);
        put(E_SKU, 40);
        put(F_SKU, 10);
    }};

    private static final Integer discountForDoubleB = 15;
    private static final Integer discountForTripleA = 20;
    private static final Integer discountForFiveAs = 50;

    public static Integer checkout(String skus) {
        if (!containsValidSkus(skus)) {
            return -1;
        }

        List<String> listOfSkusInBasket = skus.isEmpty() ? emptyList() : asList(skus.split(""));

        return listOfSkusInBasket.stream().mapToInt(priceMap::get).sum() - discount(listOfSkusInBasket);
    }

    private static int discount(List<String> listOfSkus) {
        NumberOfEachSku numberOfSkusInBasket = new NumberOfEachSku(listOfSkus);
        int numberOfASkus = numberOfSkusInBasket.numberOfSkus(A_SKU);
        int numberOfBSkus = numberOfSkusInBasket.numberOfSkus(B_SKU);
        int numberOfFSkus = numberOfSkusInBasket.numberOfSkus(F_SKU);
        DiscountForMultipleSkus discountForEachFiveAs = new DiscountForMultipleSkus(numberOfASkus, 5, discountForFiveAs);
        DiscountForMultipleSkus discountForEachTripleA = new DiscountForMultipleSkus(numberOfASkus - discountForEachFiveAs.numberOfDiscountedSkus(), 3, discountForTripleA);
        FreeSkuForNumberOfAnotherSkus freeBForEachTwoEs = new FreeSkuForNumberOfAnotherSkus(numberOfBSkus, numberOfSkusInBasket.numberOfSkus(E_SKU), 2, priceMap.get(B_SKU));
        DiscountForMultipleSkus discountForEachDoubleB = new DiscountForMultipleSkus(numberOfBSkus - freeBForEachTwoEs.numberOfDiscountedSkus(), 2, discountForDoubleB);
        DiscountForMultipleSkus freeFForEachTripleF = new DiscountForMultipleSkus(numberOfFSkus, 3, priceMap.get(F_SKU));
        return discountForEachFiveAs.discount() + discountForEachTripleA.discount() +
                freeBForEachTwoEs.discount() + discountForEachDoubleB.discount() + freeFForEachTripleF.discount();
    }

    private static boolean containsValidSkus(String skus) {
        return skus.matches("^[ABCDEF]*$");
    }

}
