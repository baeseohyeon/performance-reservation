package com.numble.performancereservation.performance;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VenueDto {

    private String name;
    private int capacity;
    private VenueType venueType;
    private String possibleTimes;
    private List<SeatDto> seats = new ArrayList<>();
}
