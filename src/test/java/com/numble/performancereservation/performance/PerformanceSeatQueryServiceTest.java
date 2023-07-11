package com.numble.performancereservation.performance;

import static org.assertj.core.api.Assertions.assertThat;

import com.numble.performancereservation.seat.Seat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PerformanceSeatQueryServiceTest {

    @Autowired
    PerformanceSeatQueryService performanceSeatQueryService;

    @Test
    void findAvailableSeatsByPerformanceId() {
        //given
        Long performanceId = 1L;

        //when
        List<Seat> availableSeats = performanceSeatQueryService.findAvailableSeatsByPerformanceId(
            performanceId);

        //then
        assertThat(availableSeats.size()).isEqualTo(1000);
    }
}