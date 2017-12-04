package befaster.solutions;

final class DiscountForMultipleSkus {
    private final int numberOfSkus;
    private final int numberOfSkusTriggeringDiscount;
    private final int discountAmount;

    DiscountForMultipleSkus(int numberOfSkus, int numberOfSkusTriggeringDiscount, int discountAmount) {
        this.numberOfSkus = numberOfSkus;
        this.numberOfSkusTriggeringDiscount = numberOfSkusTriggeringDiscount;
        this.discountAmount = discountAmount;
    }

    int numberOfDiscountedSkus() {
        return (numberOfSkus / numberOfSkusTriggeringDiscount) * numberOfSkusTriggeringDiscount;
    }

    int discount() {
        return (numberOfSkus / numberOfSkusTriggeringDiscount) * discountAmount;
    }
}
