package tg.webui;

import tg.domain.RoomType;
import tg.domain.Usage;

import java.util.Map;

import static tg.domain.RoomType.ECONOMY;
import static tg.domain.RoomType.PREMIUM;

public record UsagesDTO(UsageDTO usagePremium, UsageDTO usageEconomy) {
    public static UsagesDTO from(final Map<RoomType, Usage> usages) {
        return new UsagesDTO(UsageDTO.from(usages.get(PREMIUM)), UsageDTO.from(usages.get(ECONOMY)));
    }
}
