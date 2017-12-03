package befaster.solutions;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;

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

    public static Integer checkout(String skus) {
        List<String> listOfSkus = asList(skus.split(""));


        return (listOfSkus).stream()
                .mapToInt((String sku) -> priceMap.get(sku))
                .sum();
    }
}
