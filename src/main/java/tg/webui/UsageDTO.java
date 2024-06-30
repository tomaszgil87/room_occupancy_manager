package tg.webui;

import tg.domain.Usage;

import java.math.BigDecimal;

public record UsageDTO(Integer count, BigDecimal amount, String currency) {

    public static UsageDTO from(final Usage usage) {
        return new UsageDTO(usage.count(), usage.amount(), usage.currency());
    }
}
