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

    public void saveAll(List<Long> seatIds, Long reservationId) {
        String sql = "insert into reservation_seat (seat_id, reservation_id) values (?, ?)";
        jdbcTemplate.batchUpdate(sql, seatIds, seatIds.size(),
            (PreparedStatement ps, Long seatId) -> {
                ps.setLong(1, seatId);
                ps.setLong(2, reservationId);
            });
    }
}
