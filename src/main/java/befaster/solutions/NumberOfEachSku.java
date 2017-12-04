package befaster.solutions;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

final class NumberOfEachSku {
    private final Map<String, Integer> numberOfEachSku;

    NumberOfEachSku(List<String> listOfSkus) {
        this.numberOfEachSku = listOfSkus.stream().collect(groupingBy(Function.identity(), summingInt(e -> 1)));
    }

    int numberOfSkus(String sku) {
        return numberOfEachSku.getOrDefault(sku, 0);
    }
}
