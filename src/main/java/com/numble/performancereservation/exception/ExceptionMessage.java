package com.numble.performancereservation.exception;

public class ExceptionMessage {

    public static final String DUPLICATE_EMAIL = "email already exists";
    public static final String DUPLICATE_USERNAME = "username already exists";
    public static final String INVALID_USERID = "userId doesn't exists";
    public static final String INVALID_USER = "user isn't producer";
    public static final String INVALID_VENUE_USE_TIME = "use time of the venue must be at least 1 hour.";
    public static final String INVALID_VENUE_ID = "venueId doesn't exists";
    public static final String INVALID_PERFORMANCE_ID = "performanceId doesn't exists";
    public static final String INVALID_RESERVATION_ID = "reservationId doesn't exists";
    public static final String INVALID_SEAT_ID = "seatId doesn't exists";
    public static final String SEAT_PRICE_CANNOT_BE_NEGATIVE = "seat price can't be negative";
    public static final String VIP_SEAT_PRICE_CANNOT_BE_CHEAPER_THAN_NORMAL = "vip seat price can't be cheaper than normal";
    public static final String ALREADY_RESERVED_DURATION = "already reserved duration";
    public static final String PERFORMANCE_TIME_MUST_WITHIN_VENUE_USE_TIME = "performance time must within venue use time";
    public static final String ALREADY_RESERVED_SEAT = "already reserved seat";
    public static final String UNABLE_TO_HOLD_LOCK = "Unable to hold lock";
}
