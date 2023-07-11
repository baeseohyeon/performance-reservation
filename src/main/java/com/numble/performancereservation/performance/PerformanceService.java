package com.numble.performancereservation.performance;

import static com.numble.performancereservation.exception.ExceptionMessage.ALREADY_RESERVED_DURATION;

import com.numble.performancereservation.user.User;
import com.numble.performancereservation.venue.Venue;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PerformanceService {

    private final PerformanceRepository performanceRepository;

    public Performance save(User user, Venue venue, PerformanceDto performanceDto,
        int availableSeats) {
        Performance performance = Performance.builder()
            .user(user)
            .venue(venue)
            .capacity(performanceDto.getCapacity())
            .name(performanceDto.getName())
            .availableSeats(availableSeats)
            .dateTime(
                new PerformanceDateTime(performanceDto.getStartTime(), performanceDto.getEndTime()))
            .price(
                new PerformancePrice(performanceDto.getNormalPrice(), performanceDto.getVipPrice()))
            .build();
        return performanceRepository.save(performance);
    }

    public void validatePerformanceTimeReserved(Venue venue, LocalDateTime startTime,
        LocalDateTime endTime) {
        if(performanceRepository.existsByVenueIdAndStartTimeAndEndTime(venue.getId(), startTime, endTime)){
            throw new IllegalArgumentException(ALREADY_RESERVED_DURATION);
        }
    }
}
