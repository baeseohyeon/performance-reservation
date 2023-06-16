package com.numble.performancereservation.performance;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    @Query(value = "select case when count(*) > 0 THEN true ELSE false END from Performance p "
        + "where p.venue_id = :venueId and "
        + "(p.start_time between :startTime and :endTime) or "
        + "(p.end_time between :startTime and :endTime) or "
        + "(:startTime >= p.start_time and :endTime <= p.end_time) limit 1", nativeQuery = true)
    boolean existsByVenueIdAndStartTimeAndEndTime(Long venueId, LocalDateTime startTime, LocalDateTime endTime);
}
