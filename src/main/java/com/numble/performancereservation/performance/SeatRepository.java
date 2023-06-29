package com.numble.performancereservation.performance;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    int countByVenueId(Long venueId);

    long countByIdIn(List<Long> ids);

    List<Seat> findByVenueId(Long venueId);
}
