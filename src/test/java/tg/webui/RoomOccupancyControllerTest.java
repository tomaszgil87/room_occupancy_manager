package tg.webui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tg.domain.RoomOccupancyService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RoomOccupancyControllerTest {

    @Mock
    private RoomOccupancyService roomOccupancyService;
    @InjectMocks
    private RoomOccupancyController roomOccupancyController;

    @Test
    void shouldValidateParameters1() {
        assertThrows(IllegalArgumentException.class, () -> roomOccupancyController.getUsage(-1, 3));
    }

    @Test
    void shouldValidateParameters2() {
        assertThrows(IllegalArgumentException.class, () -> roomOccupancyController.getUsage(3, -1));
    }
}