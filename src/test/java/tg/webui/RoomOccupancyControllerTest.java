package tg.webui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RoomOccupancyControllerTest {

    @InjectMocks
    private RoomOccupancyController roomOccupancyController;

    @Test
    public void test1() {
        //when
        var occupancyDTO = roomOccupancyController.getUsage(3, 3);
        //then
        assertEquals(occupancyDTO.usagePremium().count(), 3);
        assertEquals(occupancyDTO.usagePremium().amount(), new BigDecimal("738"));
        assertEquals(occupancyDTO.usagePremium().currency(), "EUR");
        assertEquals(occupancyDTO.usageEconomy().count(), 3);
        assertEquals(occupancyDTO.usageEconomy().amount(), new BigDecimal("167.99"));
        assertEquals(occupancyDTO.usageEconomy().currency(), "EUR");
    }

    @Test
    public void test2() {
        //when
        var occupancyDTO = roomOccupancyController.getUsage(7, 5);
        //then
        assertEquals(occupancyDTO.usagePremium().count(), 6);
        assertEquals(occupancyDTO.usagePremium().amount(), new BigDecimal("1054"));
        assertEquals(occupancyDTO.usagePremium().currency(), "EUR");
        assertEquals(occupancyDTO.usageEconomy().count(), 4);
        assertEquals(occupancyDTO.usageEconomy().amount(), new BigDecimal("189.99"));
        assertEquals(occupancyDTO.usageEconomy().currency(), "EUR");
    }

    @Test
    public void test3() {
        //when
        var occupancyDTO = roomOccupancyController.getUsage(2, 7);
        //then
        assertEquals(occupancyDTO.usagePremium().count(), 2);
        assertEquals(occupancyDTO.usagePremium().amount(), new BigDecimal("583"));
        assertEquals(occupancyDTO.usagePremium().currency(), "EUR");
        assertEquals(occupancyDTO.usageEconomy().count(), 4);
        assertEquals(occupancyDTO.usageEconomy().amount(), new BigDecimal("189.99"));
        assertEquals(occupancyDTO.usageEconomy().currency(), "EUR");
    }

    @Test
    public void test4() {
        //when
        var occupancyDTO = roomOccupancyController.getUsage(7, 1);
        //then
        assertEquals(occupancyDTO.usagePremium().count(), 7);
        assertEquals(occupancyDTO.usagePremium().amount(), new BigDecimal("1153"));
        assertEquals(occupancyDTO.usagePremium().currency(), "EUR");
        assertEquals(occupancyDTO.usageEconomy().count(), 1);
        assertEquals(occupancyDTO.usageEconomy().amount(), new BigDecimal("45.99"));
        assertEquals(occupancyDTO.usageEconomy().currency(), "EUR");
    }
}
