package tg.infrastructure;

import java.math.BigDecimal;
import java.util.List;

public interface RoomOccupancyRepository {

    List<BigDecimal> getPotentialGuests();
}
