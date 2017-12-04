package befaster.solutions;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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

        Map<String, Integer> numberOfSkusInBasket = numberOfEachSkuInBasket(listOfSkusInBasket);
        DiscountForMultipleSkus discountForEachFiveAs = new DiscountForMultipleSkus(numberOfSkusInBasket.getOrDefault(A_SKU, 0), 5, discountForFiveAs);
        DiscountForMultipleSkus discountForEachTripleA = new DiscountForMultipleSkus(numberOfSkusInBasket.getOrDefault(A_SKU, 0) - discountForEachFiveAs.numberOfDiscountedSkus(), 3, discountForTripleA);
        FreeSkuForNumberOfAnotherSkus freeBForEachTwoEs = new FreeSkuForNumberOfAnotherSkus(numberOfSkusInBasket.getOrDefault(B_SKU, 0), numberOfSkusInBasket.getOrDefault(E_SKU, 0), 2, priceMap.get(B_SKU));
        DiscountForMultipleSkus discountForEachDoubleB = new DiscountForMultipleSkus(numberOfSkusInBasket.getOrDefault(B_SKU, 0) - freeBForEachTwoEs.numberOfDiscountedSkus(), 2, discountForDoubleB);
        int discounts =
                discountForEachFiveAs.discount() + discountForEachTripleA.discount() +
                freeBForEachTwoEs.discount() + discountForEachDoubleB.discount() +
                freeOneFForEachThreeFsInBasket(numberOfSkusInBasket.getOrDefault(F_SKU,0));

        return listOfSkusInBasket.stream().mapToInt(priceMap::get).sum() - discounts;
    }

    private static boolean containsValidSkus(String skus) {
        return skus.matches("^[ABCDEF]*$");
    }

    private static Map<String, Integer> numberOfEachSkuInBasket(List<String> listOfSkusInBasket) {
        return listOfSkusInBasket.stream().collect(groupingBy(Function.identity(), summingInt(e -> 1)));
    }

    private static Integer freeOneFForEachThreeFsInBasket(Integer numberOfFSkusInBasket) {
        return new DiscountForMultipleSkus(numberOfFSkusInBasket, 3, priceMap.get(F_SKU)).discount();
    }

    public static final class DiscountForMultipleSkus {
        private final int numberOfSkus;
        private final int numberOfSkusTriggeringDiscount;
        private final int discountAmount;

        public DiscountForMultipleSkus(int numberOfSkus, int numberOfSkusTriggeringDiscount, int discountAmount){
            this.numberOfSkus = numberOfSkus;
            this.numberOfSkusTriggeringDiscount = numberOfSkusTriggeringDiscount;
            this.discountAmount = discountAmount;
        }

        public int numberOfDiscountedSkus() {
            return (numberOfSkus / numberOfSkusTriggeringDiscount) * numberOfSkusTriggeringDiscount;
        }

        public int discount() {
            return (numberOfSkus / numberOfSkusTriggeringDiscount) * discountAmount;
        }


    }

    public static final class FreeSkuForNumberOfAnotherSkus {
        private final int numberOfSkusEligibleForDiscount;
        private final int numberOfAnotherSkus;
        private final int numberOfAnotherSkusTriggeringDiscount;
        private final int discountAmount;

        public FreeSkuForNumberOfAnotherSkus(int numberOfSkusEligibleForDiscount, int numberOfAnotherSkus, int numberOfAnotherSkusTriggeringDiscount, int discountAmount) {
            this.numberOfSkusEligibleForDiscount = numberOfSkusEligibleForDiscount;
            this.numberOfAnotherSkus = numberOfAnotherSkus;
            this.numberOfAnotherSkusTriggeringDiscount = numberOfAnotherSkusTriggeringDiscount;
            this.discountAmount = discountAmount;
        }

        public int numberOfDiscountedSkus() {
            return Math.min(numberOfAnotherSkus / numberOfAnotherSkusTriggeringDiscount, numberOfSkusEligibleForDiscount);
        }

        public int discount() {
            return Math.min(numberOfAnotherSkus / numberOfAnotherSkusTriggeringDiscount, numberOfSkusEligibleForDiscount) * discountAmount;
        }
    }
}
