package tg.domain;

import org.springframework.stereotype.Service;
import tg.infrastructure.RoomOccupancyRepository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RoomOccupancyService {

    private final RoomOccupancyRepository roomOccupancyRepository;

    public RoomOccupancyService(RoomOccupancyRepository roomOccupancyRepository) {
        this.roomOccupancyRepository = roomOccupancyRepository;
    }

    public Map<RoomType, Usage> getUsage(final List<OccupancyData> occupancies) {
        final var potentialGuests = roomOccupancyRepository.getPotentialGuests();
        return occupancies.stream()
                          .map(o -> o.type().compute(potentialGuests, o.freeRooms()))
                          .collect(Collectors.toMap(Usage::type, Function.identity()));
    }
}
