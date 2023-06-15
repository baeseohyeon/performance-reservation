package com.numble.performancereservation.performance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    long countByVenueId(Long venueId);
}
