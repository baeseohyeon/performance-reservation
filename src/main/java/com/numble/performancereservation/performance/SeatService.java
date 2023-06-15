package com.numble.performancereservation.performance;

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

    public void saveAll(List<SeatDto> seats, Long venueId){
        seatJdbcRepository.saveAll(seats,venueId);
    }

    public long countByVenueId(Long venueId) {
        return seatRepository.countByVenueId(venueId);
    }
}
