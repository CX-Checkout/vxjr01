package befaster.solutions;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class Checkout {
    private static final String A_SKU = "A";
    private static final String B_SKU = "B";
    private static final String C_SKU = "C";
    private static final String D_SKU = "D";
    private static final HashMap<String, Integer> priceMap = new HashMap<String, Integer>() {{
        put(A_SKU,50);
        put(B_SKU,30);
        put(C_SKU,20);
        put(D_SKU,15);
    }};

    private static final Integer discountForDoubleB = 15;

    public static Integer checkout(String skus) {
        List<String> listOfSkus = asList(skus.split(""));
        Integer numberOfBSkusInBasket = listOfSkus.stream()
                .collect(groupingBy(Function.identity(), summingInt(e -> 1))).getOrDefault(B_SKU, 0);

        return (listOfSkus).stream()
                .mapToInt((String sku) -> priceMap.get(sku))
                .sum() - ((numberOfBSkusInBasket / 2) * discountForDoubleB);
    }
}
