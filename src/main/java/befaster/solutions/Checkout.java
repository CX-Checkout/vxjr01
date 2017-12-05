package befaster.solutions;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Checkout {
    private static final String A_SKU = "A";
    private static final String B_SKU = "B";
    private static final String C_SKU = "C";
    private static final String D_SKU = "D";
    private static final String E_SKU = "E";
    private static final String F_SKU = "F";
    private static final String G_SKU = "G";
    private static final String H_SKU = "H";
    private static final String I_SKU = "I";
    private static final String J_SKU = "J";
    private static final String K_SKU = "K";
    private static final String L_SKU = "L";
    private static final String M_SKU = "M";
    private static final String N_SKU = "N";
    private static final String O_SKU = "O";
    private static final String P_SKU = "P";
    private static final String Q_SKU = "Q";
    private static final String R_SKU = "R";
    private static final String S_SKU = "S";
    private static final String T_SKU = "T";
    private static final String U_SKU = "U";
    private static final String V_SKU = "V";
    private static final String W_SKU = "W";
    private static final String X_SKU = "X";
    private static final String Y_SKU = "Y";
    private static final String Z_SKU = "Z";

    private static final HashMap<String, Integer> priceMap = new HashMap<String, Integer>() {{
        put(A_SKU, 50);
        put(B_SKU, 30);
        put(C_SKU, 20);
        put(D_SKU, 15);
        put(E_SKU, 40);
        put(F_SKU, 10);
        put(G_SKU, 20);
        put(H_SKU, 10);
        put(I_SKU, 35);
        put(J_SKU, 60);
        put(K_SKU, 70);
        put(L_SKU, 90);
        put(M_SKU, 15);
        put(N_SKU, 40);
        put(O_SKU, 10);
        put(P_SKU, 50);
        put(Q_SKU, 30);
        put(R_SKU, 50);
        put(S_SKU, 20);
        put(T_SKU, 20);
        put(U_SKU, 40);
        put(V_SKU, 50);
        put(W_SKU, 20);
        put(X_SKU, 17);
        put(Y_SKU, 20);
        put(Z_SKU, 21);
    }};

    private static final int discountForDoubleB = 15;
    private static final int discountForTripleA = 20;
    private static final int discountForFiveAs = 50;
    private static final int discountForFiveHs = 5;
    private static final int discountForTenHs = 20;
    private static final int discountForDoubleK = 20;
    private static final int discountForFivePs = 50;
    private static final int discountForTripleQ = 10;
    private static final int discountForDoubleV = 10;
    private static final int discountForTripleV = 20;

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
        int numberOfESkus = numberOfSkusInBasket.numberOfSkus(E_SKU);
        int numberOfFSkus = numberOfSkusInBasket.numberOfSkus(F_SKU);
        int numberOfHSkus = numberOfSkusInBasket.numberOfSkus(H_SKU);
        int numberOfKSkus = numberOfSkusInBasket.numberOfSkus(K_SKU);
        int numberOfMSkus = numberOfSkusInBasket.numberOfSkus(M_SKU);
        int numberOfNSkus = numberOfSkusInBasket.numberOfSkus(N_SKU);
        int numberOfPSkus = numberOfSkusInBasket.numberOfSkus(P_SKU);
        int numberOfQSkus = numberOfSkusInBasket.numberOfSkus(Q_SKU);
        int numberOfRSkus = numberOfSkusInBasket.numberOfSkus(R_SKU);
        int numberOfUSkus = numberOfSkusInBasket.numberOfSkus(U_SKU);
        int numberOfVSkus = numberOfSkusInBasket.numberOfSkus(V_SKU);

        DiscountForMultipleSkus discountForEachFiveAs = new DiscountForMultipleSkus(numberOfASkus, 5, discountForFiveAs);
        DiscountForMultipleSkus discountForEachTripleA = new DiscountForMultipleSkus(
                numberOfASkus - discountForEachFiveAs.numberOfDiscountedSkus(), 3, discountForTripleA);

        DiscountForMultipleSkus discountForEachTenHs = new DiscountForMultipleSkus(numberOfHSkus, 10, discountForTenHs);
        DiscountForMultipleSkus discountForEachFiveHs = new DiscountForMultipleSkus(
                numberOfHSkus - discountForEachTenHs.numberOfDiscountedSkus(), 5, discountForFiveHs);

        DiscountForMultipleSkus discountForEachTripleV = new DiscountForMultipleSkus(numberOfVSkus, 3, discountForTripleV);
        DiscountForMultipleSkus discountForEachDoubleV = new DiscountForMultipleSkus(
                numberOfVSkus - discountForEachTripleV.numberOfDiscountedSkus(), 2, discountForDoubleV);

        FreeSkuForNumberOfAnotherSkus freeBForEachTwoEs = new FreeSkuForNumberOfAnotherSkus(numberOfBSkus,
                numberOfESkus, 2, priceMap.get(B_SKU));
        DiscountForMultipleSkus discountForEachDoubleB = new DiscountForMultipleSkus(
                numberOfBSkus - freeBForEachTwoEs.numberOfDiscountedSkus(), 2, discountForDoubleB);

        FreeSkuForNumberOfAnotherSkus freeQForEachTripleR = new FreeSkuForNumberOfAnotherSkus(numberOfQSkus,
                numberOfRSkus, 3, priceMap.get(Q_SKU));
        DiscountForMultipleSkus discountForEachTripleQ = new DiscountForMultipleSkus(
                numberOfQSkus - freeQForEachTripleR.numberOfDiscountedSkus(), 3, discountForTripleQ);

        DiscountForMultipleSkus discountForEachDoubleK = new DiscountForMultipleSkus(numberOfKSkus, 2, discountForDoubleK);
        DiscountForMultipleSkus discountForEachFivePs = new DiscountForMultipleSkus(numberOfPSkus, 5, discountForFivePs);

        FreeSkuForNumberOfAnotherSkus freeMForEachTripleN = new FreeSkuForNumberOfAnotherSkus(numberOfMSkus,
                numberOfNSkus, 3, priceMap.get(M_SKU));

        DiscountForMultipleSkus freeFForEachTripleF = new DiscountForMultipleSkus(numberOfFSkus, 3, priceMap.get(F_SKU));
        DiscountForMultipleSkus freeUForEachFourU = new DiscountForMultipleSkus(numberOfUSkus, 4, priceMap.get(U_SKU));

        int totalGroupDiscount = groupDiscountForSTXYZSkus(listOfSkus, numberOfSkusInBasket);

        return
            discountForEachFiveAs.discount() + discountForEachTripleA.discount() +
            discountForEachTenHs.discount() + discountForEachFiveHs.discount() +
            freeBForEachTwoEs.discount() + discountForEachDoubleB.discount() +
            discountForEachDoubleK.discount() +
            freeFForEachTripleF.discount() +
            freeMForEachTripleN.discount() +
            discountForEachFivePs.discount() +
            freeQForEachTripleR.discount() + discountForEachTripleQ.discount() +
            freeUForEachFourU.discount() +
            discountForEachTripleV.discount() + discountForEachDoubleV.discount() + totalGroupDiscount;
    }

    private static int groupDiscountForSTXYZSkus(List<String> listOfSkus, NumberOfEachSku numberOfSkusInBasket) {
        int discountForGroup = 45;
        int numberOfSkusFromGroupEligibleForDiscount = 3;
        int numberOfAllSkusFromGroup = numberOfSkusInBasket.numberOfSkus(S_SKU) +
                numberOfSkusInBasket.numberOfSkus(T_SKU) +
                numberOfSkusInBasket.numberOfSkus(X_SKU) +
                numberOfSkusInBasket.numberOfSkus(Y_SKU) +
                numberOfSkusInBasket.numberOfSkus(Z_SKU);
        int totalGroupDiscount = 0;
        if ((numberOfAllSkusFromGroup / numberOfSkusFromGroupEligibleForDiscount) > 0) {
            totalGroupDiscount = listOfSkus.stream()
                    .filter((sku) -> sku.matches("^[STXYZ]$"))
                    .mapToInt(priceMap::get)
                    .boxed()
                    .sorted(Collections.reverseOrder())
                    .mapToInt(Integer::intValue)
                    .limit(((long)(numberOfAllSkusFromGroup / numberOfSkusFromGroupEligibleForDiscount)) * numberOfSkusFromGroupEligibleForDiscount)
                    .sum() -
                    (numberOfAllSkusFromGroup / numberOfSkusFromGroupEligibleForDiscount) * discountForGroup;
        }
        return totalGroupDiscount;
    }

    private static boolean containsValidSkus(String skus) {
        return skus.matches("^[ABCDEFGHIJKLMNOUPQRSTUVWXYZ]*$");
    }

}
