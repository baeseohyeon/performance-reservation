package com.numble.performancereservation.performance;

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
    PerformanceService performanceService;

    @Test
    void registrationPerformance() {
        //given
        Long producerId = 3L;
        Long venueId = 1L;
        int capacity = 10000;
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(1);
        String name = "bae hall";
        int normalPrice = 10000;
        int vipPrice = 50000;
        PerformanceDto performanceDto = createPerformanceDto(venueId, capacity, startTime, endTime, name, normalPrice,
            vipPrice);

        //when
        Performance performance = performanceRegistrationService.registration(performanceDto, producerId);

        //then
        Performance findPerformance = performanceService.findById(performance.getId());
        assertThat(performance.getId()).isEqualTo(findPerformance.getId());
    }

    @Test
    void registrationPerformance_alreadyReservedTime() {
        //given
        Long producerId = 3L;
        Long venueId = 1L;
        int capacity = 10000;
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(2);
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

    PerformanceDto createPerformanceDto(Long venueId, int capacity, LocalDateTime startTime, LocalDateTime endTime,
        String name, int normalPrice, int vipPrice) {
        PerformanceDto performanceDto = new PerformanceDto();
        performanceDto.setVenueId(venueId);
        performanceDto.setCapacity(capacity);
        performanceDto.setStartTime(startTime);
        performanceDto.setEndTime(endTime);
        performanceDto.setName(name);
        performanceDto.setNormalPrice(normalPrice);
        performanceDto.setVipPrice(vipPrice);
        return performanceDto;
    }
}