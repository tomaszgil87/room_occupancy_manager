package tg.domain;

import java.math.BigDecimal;

public record Usage(RoomType type, int count, BigDecimal amount, String currency) {

    static Usage empty(final RoomType type, final String currency) {
        return new Usage(type, 0, new BigDecimal("0"), currency);
    }

    Usage add(final BigDecimal amount) {
        return new Usage(type, count + 1, this.amount.add(amount), currency);
    }

    Usage add(final Usage secondUsage) {
        return new Usage(type, this.count + secondUsage.count, this.amount.add(secondUsage.amount), currency);
    }
}
