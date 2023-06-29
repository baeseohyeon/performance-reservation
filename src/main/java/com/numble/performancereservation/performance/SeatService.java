package com.numble.performancereservation.performance;

import static com.numble.performancereservation.exception.ExceptionMessage.INVALID_SEAT_ID;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final SeatJdbcRepository seatJdbcRepository;

    public void saveAll(List<SeatDto> seats, Long venueId) {
        seatJdbcRepository.saveAll(seats, venueId);
    }

    public int countByVenueId(Long venueId) {
        return seatRepository.countByVenueId(venueId);
    }

    public void validateSeatExistence(List<Long> seatIds) {
        if (seatRepository.countByIdIn(seatIds) != seatIds.size()) {
            throw new IllegalArgumentException(INVALID_SEAT_ID);
        }
    }

    public List<Seat> findSeatsByVenueId(Long venueId){
        return seatRepository.findByVenueId(venueId);
    }
}
