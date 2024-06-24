package tg.webui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("occupancy")
public class RoomOccupancyController {


    @GetMapping
    public OccupancyDTO getUsage(@RequestParam Integer freePremium, @RequestParam Integer freeEconomy) {
        return null;
    }
}
