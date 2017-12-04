package befaster.solutions;

final class FreeSkuForNumberOfAnotherSkus {
    private final int numberOfSkusEligibleForDiscount;
    private final int numberOfAnotherSkus;
    private final int numberOfAnotherSkusTriggeringDiscount;
    private final int discountAmount;

    FreeSkuForNumberOfAnotherSkus(int numberOfSkusEligibleForDiscount, int numberOfAnotherSkus,
            int numberOfAnotherSkusTriggeringDiscount, int discountAmount) {
        this.numberOfSkusEligibleForDiscount = numberOfSkusEligibleForDiscount;
        this.numberOfAnotherSkus = numberOfAnotherSkus;
        this.numberOfAnotherSkusTriggeringDiscount = numberOfAnotherSkusTriggeringDiscount;
        this.discountAmount = discountAmount;
    }

    int numberOfDiscountedSkus() {
        return Math.min(numberOfAnotherSkus / numberOfAnotherSkusTriggeringDiscount, numberOfSkusEligibleForDiscount);
    }

    int discount() {
        return Math.min(numberOfAnotherSkus / numberOfAnotherSkusTriggeringDiscount,
                numberOfSkusEligibleForDiscount) * discountAmount;
    }
}
