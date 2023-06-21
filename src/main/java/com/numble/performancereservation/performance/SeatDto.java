package com.numble.performancereservation.performance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {

    private Long seatId;
    private String number;
    private SeatType type;

    public SeatDto(String number, SeatType type) {
        this.number = number;
        this.type = type;
    }

    public SeatDto(Seat seat) {
        seatId = seat.getId();
        number = seat.getNumber();
        type = seat.getType();
    }
}
