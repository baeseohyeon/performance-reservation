package com.numble.performancereservation.performance;

import static com.numble.performancereservation.factory.DtoFactory.createPaymentDto;
import static com.numble.performancereservation.factory.DtoFactory.createReservationSeatDtoList;
import static org.assertj.core.api.Assertions.assertThat;

import com.numble.performancereservation.payment.PaymentDto;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class ConcurrentPerformanceReservationServiceTest {

    @Autowired
    ConcurrentPerformanceReservationService performanceReservationService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    SeatService seatService;
    @Autowired
    ReservationRepository reservationRepository;
    Logger log = LoggerFactory.getLogger(getClass().getName());

    @Test
    void reservationPerformance() throws InterruptedException {
        //given
        Long performanceId = 1L;
        int totalPrice = 200000;
        PaymentDto paymentDto = createPaymentDto();
        List<SeatDto> seats = createReservationSeatDtoList();
        PerformanceReservationRequest request = new PerformanceReservationRequest(performanceId,
            totalPrice, paymentDto, seats);

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        //when
        AtomicLong atomicLong = new AtomicLong(1);
        for (long i = 1; i <= 100; i++) {
            executorService.submit(() -> {
                try {
                    performanceReservationService.reservationPerformance(request,
                        atomicLong.getAndIncrement());
                } catch (IllegalArgumentException e) {
                    log.info("exception = {}",e.getMessage());
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        //then
        assertThat(reservationService.countByPerformanceId(performanceId)).isEqualTo(1);
    }
}