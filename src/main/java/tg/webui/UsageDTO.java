package tg.webui;

import java.math.BigDecimal;

public record UsageDTO(Integer count, BigDecimal amount, String currency) {
}
