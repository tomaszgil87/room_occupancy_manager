package tg.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tg.infrastructure.MockOccupancyImpl;
import tg.infrastructure.RoomOccupancyRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tg.domain.RoomType.ECONOMY;
import static tg.domain.RoomType.PREMIUM;

@ExtendWith(MockitoExtension.class)
public class RoomOccupancyServiceTest {

    private final RoomOccupancyRepository roomOccupancyRepository = new MockOccupancyImpl();

    private final RoomOccupancyService roomOccupancyService = new RoomOccupancyService(roomOccupancyRepository);

    @Test
    public void test1() {
        //given
        OccupancyData dataPremium = new OccupancyData(PREMIUM, 3);
        OccupancyData dataEconomy = new OccupancyData(ECONOMY, 3);
        List<OccupancyData> occupancies = List.of(dataPremium, dataEconomy);
        //when
        var usages = roomOccupancyService.getUsage(occupancies);
        //then
        assertEquals(3, usages.get(PREMIUM).count());
        assertEquals(new BigDecimal("738"), usages.get(PREMIUM).amount());
        assertEquals("EUR", usages.get(PREMIUM).currency());
        assertEquals(3, usages.get(ECONOMY).count());
        assertEquals(new BigDecimal("167.99"), usages.get(ECONOMY).amount());
        assertEquals("EUR", usages.get(ECONOMY).currency());
    }

    @Test
    public void test2() {
        //given
        OccupancyData dataPremium = new OccupancyData(PREMIUM, 7);
        OccupancyData dataEconomy = new OccupancyData(ECONOMY, 5);
        List<OccupancyData> occupancies = List.of(dataPremium, dataEconomy);
        //when
        var usages = roomOccupancyService.getUsage(occupancies);
        //then
        assertEquals(usages.get(PREMIUM).count(), 6);
        assertEquals(usages.get(PREMIUM).amount(), new BigDecimal("1054"));
        assertEquals(usages.get(PREMIUM).currency(), "EUR");
        assertEquals(usages.get(ECONOMY).count(), 4);
        assertEquals(usages.get(ECONOMY).amount(), new BigDecimal("189.99"));
        assertEquals(usages.get(ECONOMY).currency(), "EUR");
    }

    @Test
    public void test3() {
        //given
        OccupancyData dataPremium = new OccupancyData(PREMIUM, 2);
        OccupancyData dataEconomy = new OccupancyData(ECONOMY, 7);
        List<OccupancyData> occupancies = List.of(dataPremium, dataEconomy);
        //when
        var usages = roomOccupancyService.getUsage(occupancies);
        //then
        assertEquals(2, usages.get(PREMIUM).count());
        assertEquals(new BigDecimal("583"), usages.get(PREMIUM).amount());
        assertEquals("EUR", usages.get(PREMIUM).currency());
        assertEquals(4, usages.get(ECONOMY).count());
        assertEquals(new BigDecimal("189.99"), usages.get(ECONOMY).amount());
        assertEquals("EUR", usages.get(ECONOMY).currency());
    }

    @Test
    //I don't understand below scenario, in my opinion it is wrong and fails
    public void test4() {
        //given
        OccupancyData dataPremium = new OccupancyData(PREMIUM, 7);
        OccupancyData dataEconomy = new OccupancyData(ECONOMY, 1);
        List<OccupancyData> occupancies = List.of(dataPremium, dataEconomy);
        //when
        var usages = roomOccupancyService.getUsage(occupancies);
        //then
        assertEquals(7, usages.get(PREMIUM).count());
        assertEquals(new BigDecimal("1153"), usages.get(PREMIUM).amount());
        assertEquals("EUR", usages.get(PREMIUM).currency());
        assertEquals(1, usages.get(ECONOMY).count());
        assertEquals(new BigDecimal("45.99"), usages.get(ECONOMY).amount());
        assertEquals("EUR", usages.get(ECONOMY).currency());
    }
}
