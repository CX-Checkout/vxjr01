package befaster.solutions;

import static java.util.Arrays.asList;

import java.util.HashMap;

public class Checkout {
    private static final HashMap<String, Integer> priceMap = new HashMap<String, Integer>() {{
        put("A",50);
        put("B",30);
        put("C",20);
        put("D",15);
    }};

    public static Integer checkout(String skus) {
        asList(skus.split("")).stream()
                .mapToInt((String sku) -> priceMap.get(sku))
                .flatMap();
        return priceMap.get(skus);
    }
}
