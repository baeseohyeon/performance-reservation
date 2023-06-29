package com.numble.performancereservation.performance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationSeatRepository extends JpaRepository<ReservationSeat, Long> {


    long countByReservation(Reservation reservation);
}
