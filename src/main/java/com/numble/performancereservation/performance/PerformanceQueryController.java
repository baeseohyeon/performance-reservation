package com.numble.performancereservation.performance;

import com.numble.performancereservation.common.ApiResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class PerformanceQueryController {

    private final PerformanceQueryService performanceQueryService;

    @GetMapping("/performances")
    public ApiResult<List<PerformanceResponseDto>> findPerformances(
        @PageableDefault(size = 20, sort = "dateTime.startTime", direction = Sort.Direction.ASC) Pageable pageable) {
        List<PerformanceResponseDto> result = performanceQueryService.findPerformancesOrderByStartTimeAsc(
                pageable)
            .stream().map(PerformanceResponseDto::new).toList();
        long count = performanceQueryService.count();

        return new ApiResult<>(count, result);
    }

    @GetMapping("/performances/{performanceId}")
    public ApiResult<PerformanceResponseDto> findPerformance(@PathVariable Long performanceId) {
        return new ApiResult<>(performanceQueryService.findByIdInRedis(performanceId));
    }
}
