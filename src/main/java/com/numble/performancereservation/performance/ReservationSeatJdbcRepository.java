package com.numble.performancereservation.performance;

import java.sql.PreparedStatement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Repository
public class ReservationSeatJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveAll(List<SeatDto> seats, Long reservationId) {
        String sql = "insert into reservation_seat (seat_id, reservation_id) values (?, ?)";
        jdbcTemplate.batchUpdate(sql, seats, seats.size(),
            (PreparedStatement ps, SeatDto seat) -> {
                ps.setLong(1, seat.getSeatId());
                ps.setLong(2, reservationId);
            });
    }
}
