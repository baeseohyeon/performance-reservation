package com.numble.performancereservation.performance;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    long countByVenueId(Long venueId);

    long countByIdIn(List<Long> ids);

    boolean existsByIdInAndIsReserved(Collection<Long> ids, boolean isReserved);
}
