package tg.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tg.infrastructure.RoomOccupancyRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tg.domain.RoomType.ECONOMY;
import static tg.domain.RoomType.PREMIUM;

@ExtendWith(MockitoExtension.class)
public class RoomOccupancyServiceTest {

    @Mock
    private RoomOccupancyRepository roomOccupancyRepository;

    @InjectMocks
    private RoomOccupancyService roomOccupancyService;

    @Test
    public void test1() {
        //given
        OccupancyData dataPremium = new OccupancyData(PREMIUM, 3);
        OccupancyData dataEconomy = new OccupancyData(ECONOMY, 3);
        List<OccupancyData> occupancies = List.of(dataPremium, dataEconomy);
        //when
        var usages = roomOccupancyService.getUsage(occupancies);
        //then
        assertEquals(usages.get(PREMIUM).count(), 3);
        assertEquals(usages.get(PREMIUM).amount(), new BigDecimal("738"));
        assertEquals(usages.get(PREMIUM).currency(), "EUR");
        assertEquals(usages.get(ECONOMY).count(), 3);
        assertEquals(usages.get(ECONOMY).amount(), new BigDecimal("167.99"));
        assertEquals(usages.get(ECONOMY).currency(), "EUR");
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
        assertEquals(usages.get(PREMIUM).count(), 2);
        assertEquals(usages.get(PREMIUM).amount(), new BigDecimal("583"));
        assertEquals(usages.get(PREMIUM).currency(), "EUR");
        assertEquals(usages.get(ECONOMY).count(), 4);
        assertEquals(usages.get(ECONOMY).amount(), new BigDecimal("189.99"));
        assertEquals(usages.get(ECONOMY).currency(), "EUR");
    }

    @Test
    public void test4() {
        //given
        OccupancyData dataPremium = new OccupancyData(PREMIUM, 7);
        OccupancyData dataEconomy = new OccupancyData(ECONOMY, 1);
        List<OccupancyData> occupancies = List.of(dataPremium, dataEconomy);
        //when
        var usages = roomOccupancyService.getUsage(occupancies);
        //then
        assertEquals(usages.get(PREMIUM).count(), 7);
        assertEquals(usages.get(PREMIUM).amount(), new BigDecimal("1153"));
        assertEquals(usages.get(PREMIUM).currency(), "EUR");
        assertEquals(usages.get(ECONOMY).count(), 1);
        assertEquals(usages.get(ECONOMY).amount(), new BigDecimal("45.99"));
        assertEquals(usages.get(ECONOMY).currency(), "EUR");
    }
}
