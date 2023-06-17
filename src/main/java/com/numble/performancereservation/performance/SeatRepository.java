package com.numble.performancereservation.performance;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    long countByVenueId(Long venueId);

    long countByIdIn(List<Long> ids);

    boolean existsByIdInAndIsReserved(Collection<Long> ids, boolean isReserved);

    @Query("update Seat s set s.isReserved = true, s.reservation = :reservation where s.id in :ids")
    @Modifying
    int updateIsReservedToTrueAndReservationByIdIn(@Param("ids") List<Long> ids,
        @Param("reservation") Reservation reservation);

    long countByIdInAndIsReserved(List<Long> ids, boolean isReserved);
}
