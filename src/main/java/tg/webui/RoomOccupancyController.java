package tg.webui;

import org.springframework.web.bind.annotation.*;
import tg.domain.OccupancyData;
import tg.domain.RoomOccupancyService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static tg.domain.RoomType.ECONOMY;
import static tg.domain.RoomType.PREMIUM;

@RestController
@RequestMapping("occupancy")
public class RoomOccupancyController {

    private final RoomOccupancyService roomOccupancyService;

    public RoomOccupancyController(final RoomOccupancyService roomOccupancyService) {
        this.roomOccupancyService = roomOccupancyService;
    }

    @GetMapping
    @ResponseStatus(OK)
    public UsagesDTO getUsage(@RequestParam Integer freePremium, @RequestParam Integer freeEconomy) {
        validate(freePremium, freeEconomy);
        List<OccupancyData> occupancies = List.of(new OccupancyData(PREMIUM, freePremium),
                                                  new OccupancyData(ECONOMY, freeEconomy));
        return UsagesDTO.from(roomOccupancyService.getUsage(occupancies));
    }

    private void validate(final int freePremium, final int freeEconomy) {
        if(freePremium < 0) {
            throw new IllegalArgumentException("Parameter freePremium cannot be less than 0");
        }
        if(freeEconomy < 0) {
            throw new IllegalArgumentException("Parameter freeEconomy cannot be less than 0");
        }
    }
}
