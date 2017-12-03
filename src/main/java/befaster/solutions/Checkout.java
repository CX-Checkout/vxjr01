package befaster.solutions;

import static java.util.Arrays.asList;
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

    private static final HashMap<String, Integer> priceMap = new HashMap<String, Integer>() {{
        put(A_SKU, 50);
        put(B_SKU, 30);
        put(C_SKU, 20);
        put(D_SKU, 15);
    }};

    private static final Integer discountForDoubleB = 15;
    private static final Integer discountForTripleA = 20;

    public static Integer checkout(String skus) {
        if (containsValidSkus(skus)) {
            return -1;
        }

        List<String> listOfSkusInBasket = asList(skus.split(""));

        Map<String, Integer> numberOfSkusInBasket = numberOfEachSkuInBasket(listOfSkusInBasket);
        int discounts =
                discountForEachDoubleBSkuInBasket(numberOfSkusInBasket.getOrDefault(B_SKU, 0)) +
                discountForEachTripleASkuInBasket(numberOfSkusInBasket.getOrDefault(A_SKU, 0));

        return listOfSkusInBasket.stream().mapToInt(priceMap::get).sum() - discounts;
    }

    private static boolean containsValidSkus(String skus) {
        return !skus.matches("[ABCD]*");
    }

    private static Map<String, Integer> numberOfEachSkuInBasket(List<String> listOfSkusInBasket) {
        return listOfSkusInBasket.stream().collect(groupingBy(Function.identity(), summingInt(e -> 1)));
    }

    private static int discountForEachDoubleBSkuInBasket(Integer numberOfBSkusInBasket) {
        return (numberOfBSkusInBasket / 2) * discountForDoubleB;
    }

    private static int discountForEachTripleASkuInBasket(Integer numberOfASkusInBasket) {
        return (numberOfASkusInBasket / 3) * discountForTripleA;
    }
}
