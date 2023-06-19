package com.numble.performancereservation.performance;

import static com.numble.performancereservation.exception.ExceptionMessage.UNABLE_TO_HOLD_LOCK;

import com.numble.performancereservation.exception.RLockUnAvailableException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConcurrentPerformanceReservationService {

    private final RedissonClient redissonClient;
    private final PerformanceReservationService performanceReservationService;

    public Reservation reservationPerformance(PerformanceReservationRequest request, Long userId) {
        RLock multiRLock = getMultiRLock(request);
        try {
            validateRLockAvailable(multiRLock);
            return performanceReservationService.reservationPerformance(request, userId);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            multiRLock.unlock();
        }
    }

    private static void validateRLockAvailable(RLock multiRLock) throws InterruptedException {
        boolean unavailable = !multiRLock.tryLock(10, 3, TimeUnit.SECONDS);
        if (unavailable) {
            throw new RLockUnAvailableException(UNABLE_TO_HOLD_LOCK);
        }
    }

    private RLock getMultiRLock(PerformanceReservationRequest request) {
        List<String> seatKeys = request.getSeatIds().stream().map((id) -> "seat:" + id).toList();
        RLock[] locks = new RLock[seatKeys.size()];
        for (int i = 0; i < seatKeys.size(); i++) {
            String key = seatKeys.get(i);
            locks[i] = redissonClient.getLock(key);
        }
        return redissonClient.getMultiLock(locks);
    }
}
