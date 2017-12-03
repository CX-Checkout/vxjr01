package befaster.solutions;

import java.util.HashMap;

public class Checkout {
    private static final HashMap<String, Integer> priceMap = new HashMap<String, Integer>() {{
        put("A",50);
        put("B",30);
        put("C",20);
        put("D",15);
    }};

    public static Integer checkout(String skus) {
        return priceMap.get(skus);
    }
}
