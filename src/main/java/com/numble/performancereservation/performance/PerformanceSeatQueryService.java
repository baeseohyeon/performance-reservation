package com.numble.performancereservation.performance;

import com.numble.performancereservation.seat.Seat;
import com.numble.performancereservation.seat.SeatService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PerformanceSeatQueryService {

    private final PerformanceQueryService performanceQueryService;
    private final SeatService seatService;

    public List<Seat> findAvailableSeatsByPerformanceId(Long performanceId) {
        Performance performance = performanceQueryService.findById(performanceId);
        return seatService.findSeatsByVenueId(performance.getVenue().getId());
    }
}
