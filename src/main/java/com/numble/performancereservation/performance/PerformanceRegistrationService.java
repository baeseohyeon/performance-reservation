package com.numble.performancereservation.performance;

import com.numble.performancereservation.user.ProducerType;
import com.numble.performancereservation.user.User;
import com.numble.performancereservation.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PerformanceRegistrationService {

    private final PerformanceService performanceService;
    private final VenueService venueService;
    private final UserService userService;

    public Performance registration(PerformanceDto performanceDto, Long userId) {
        User user = userService.findProducerByIdAndType(userId, ProducerType.PERFORMANCE);
        Venue venue = venueService.findById(performanceDto.getVenueId());
        validatePerformanceTime(venue, performanceDto);
        return performanceService.save(user, venue, performanceDto);
    }

    private void validatePerformanceTime(Venue venue, PerformanceDto performanceDto) {
        venue.validatePerformanceTimeWithinRange(performanceDto.getStartTime().toLocalTime(),
            performanceDto.getEndTime().toLocalTime());

        performanceService.validatePerformanceTimeReserved(venue, performanceDto.getStartTime(),
            performanceDto.getEndTime());
    }
}
