package tg.domain;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static tg.domain.RoomType.ECONOMY;
import static tg.domain.RoomType.PREMIUM;

final class PotentialRevenueComputer {

    private static final int LIMIT = 100;
    private static final String EUR = "EUR";

    private PotentialRevenueComputer() {}

    static Usage computePremiumRevenue(final List<BigDecimal> potentialGuests, final int freeRooms) {
        return potentialGuests.stream()
                              .filter(p -> p.intValue() >= LIMIT)
                              .sorted(Comparator.reverseOrder())
                              .limit(freeRooms)
                              .reduce(Usage.empty(PREMIUM, EUR),
                                      Usage::add,
                                      Usage::add);
    }

    static Usage computeEconomyRevenue(final List<BigDecimal> potentialGuests, final int freeRooms) {
        return potentialGuests.stream()
                              .filter(p -> p.intValue() < LIMIT)
                              .sorted(Comparator.reverseOrder())
                              .limit(freeRooms)
                              .reduce(Usage.empty(ECONOMY, EUR),
                                      Usage::add,
                                      Usage::add);
    }
}
