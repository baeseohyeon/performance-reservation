package com.numble.performancereservation.performance;

import static com.numble.performancereservation.factory.DtoFactory.createPerformanceDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class PerformanceRegistrationServiceTest {

    @Autowired
    PerformanceRegistrationService performanceRegistrationService;
    @Autowired
    PerformanceQueryService performanceQueryService;

    @Test
    void registrationPerformance() {
        //given
        Long producerId = 2L;
        Long venueId = 1L;
        int capacity = 10000;
        LocalDateTime startTime = LocalDateTime.of(2023,6,17,10,0,0);
        LocalDateTime endTime = startTime.plusHours(1);
        String name = "bae hall";
        int normalPrice = 10000;
        int vipPrice = 50000;
        PerformanceDto performanceDto = createPerformanceDto(venueId, capacity, startTime, endTime, name, normalPrice,
            vipPrice);

        //when
        Performance performance = performanceRegistrationService.registration(performanceDto, producerId);

        //then
        Performance findPerformance = performanceQueryService.findById(performance.getId());
        assertThat(performance.getId()).isEqualTo(findPerformance.getId());
    }

    @Test
    void registrationPerformance_alreadyReservedTime() {
        //given
        Long producerId = 2L;
        Long venueId = 1L;
        int capacity = 10000;
        LocalDateTime startTime = LocalDateTime.of(2023,6,17,10,0,0);
        LocalDateTime endTime = startTime.plusHours(1);
        String name = "bae hall";
        int normalPrice = 10000;
        int vipPrice = 50000;
        PerformanceDto performanceDto = createPerformanceDto(venueId, capacity, startTime, endTime, name, normalPrice,
            vipPrice);
        performanceRegistrationService.registration(performanceDto, producerId);

        //when
        LocalDateTime startTimeBetweenReservedStartAndEnd = startTime.plusHours(1);
        LocalDateTime endTimeAfterReservedEnd = endTime.plusHours(1);
        PerformanceDto reservedTimePerformanceDto = createPerformanceDto(venueId, capacity,
            startTimeBetweenReservedStartAndEnd, endTimeAfterReservedEnd, name, normalPrice, vipPrice);

        //then
        assertThatThrownBy(() -> performanceRegistrationService.registration(reservedTimePerformanceDto, producerId))
            .isInstanceOf(IllegalArgumentException.class);
    }
}