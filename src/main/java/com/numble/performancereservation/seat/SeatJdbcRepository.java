package com.numble.performancereservation.seat;

import java.sql.PreparedStatement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Repository
public class SeatJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveAll(List<SeatDto> seats, Long venueId) {
        String sql = "insert into seat (number, type, venue_id) values (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, seats, seats.size(),
            (PreparedStatement ps, SeatDto seat) -> {
                ps.setString(1, seat.getNumber());
                ps.setString(2, seat.getType().name());
                ps.setLong(3, venueId);
            });
    }
}
