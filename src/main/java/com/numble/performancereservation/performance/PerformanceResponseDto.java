package com.numble.performancereservation.performance;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PerformanceResponseDto {

    private Long performanceId;
    private String title;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startTime;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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
