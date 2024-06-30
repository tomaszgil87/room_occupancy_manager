package tg.infrastructure;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class MockOccupancyImpl implements RoomOccupancyRepository {

    @Override
    public List<BigDecimal> getPotentialGuests() {
        return List.of(
                new BigDecimal("23"),
                new BigDecimal("45"),
                new BigDecimal("155"),
                new BigDecimal("374"),
                new BigDecimal("22"),
                new BigDecimal("99.99"),
                new BigDecimal("100"),
                new BigDecimal("101"),
                new BigDecimal("115"),
                new BigDecimal("209"));
    }
}
