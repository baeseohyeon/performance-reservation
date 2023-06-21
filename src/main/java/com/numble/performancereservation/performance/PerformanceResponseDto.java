package com.numble.performancereservation.performance;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PerformanceResponseDto {

    private Long performanceId;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String venueName;
    private int normalPrice;
    private int vipPrice;
    private int availableSeats;

    public PerformanceResponseDto(Performance performance) {
        performanceId=performance.getId();
        title = performance.getName();
        startTime = performance.getDateTime().getStartTime();
        endTime = performance.getDateTime().getEndTime();
        venueName = performance.getVenue().getName();
        normalPrice = performance.getPrice().getNormalPrice();
        vipPrice = performance.getPrice().getVipPrice();
        availableSeats = performance.getAvailableSeats();
    }
}
