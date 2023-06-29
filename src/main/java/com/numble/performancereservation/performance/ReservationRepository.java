package com.numble.performancereservation.performance;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    long countByPerformanceId(Long performanceId);

    @Query(value = "select case when count(*) > 0 then true else false end from reservation r "
        + "join reservation_seat rs on r.id = rs.reservation_id "
        + "where r.performance_id = :performanceId and rs.seat_id in :seatIds limit 1", nativeQuery = true)
    boolean existsByReservationAndSeatIdIn(@Param("performanceId") Long performanceId,
        @Param("seatIds") List<Long> seatIds);
}
