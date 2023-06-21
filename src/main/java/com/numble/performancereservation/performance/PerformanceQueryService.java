package com.numble.performancereservation.performance;

import static com.numble.performancereservation.exception.ExceptionMessage.INVALID_PERFORMANCE_ID;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PerformanceQueryService {

    private final PerformanceRepository performanceRepository;

    public Performance findById(Long id) {
        return performanceRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(INVALID_PERFORMANCE_ID));
    }

    public List<Performance> findPerformancesOrderByStartTimeAsc(Pageable pageable) {
        return performanceRepository.findAll(pageable).getContent();
    }

    public long count() {
        return performanceRepository.count();
    }
}
