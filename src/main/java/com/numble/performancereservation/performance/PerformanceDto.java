package com.numble.performancereservation.performance;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceDto {

    private Long performanceId;
    private Long venueId;
    private String name;
    private int capacity;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int normalPrice;
    private int vipPrice;
}
