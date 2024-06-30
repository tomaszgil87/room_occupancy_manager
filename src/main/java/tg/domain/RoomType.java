package tg.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

public enum RoomType {
    PREMIUM(PotentialRevenueComputer::computePremiumRevenue),
    ECONOMY(PotentialRevenueComputer::computeEconomyRevenue);

    private final BiFunction<List<BigDecimal>, Integer, Usage> algorithm;

    RoomType(BiFunction<List<BigDecimal>, Integer, Usage> algorithm) {
        this.algorithm = algorithm;
    }

    Usage compute(final List<BigDecimal> potentialGuests, final Integer freeRooms) {
        return algorithm.apply(potentialGuests, freeRooms);
    }
}
