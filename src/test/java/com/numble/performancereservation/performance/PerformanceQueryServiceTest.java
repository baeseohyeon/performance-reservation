package com.numble.performancereservation.performance;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootTest
public class PerformanceQueryServiceTest {

    @Autowired
    PerformanceQueryService performanceQueryService;

    @Test
    void findPerformances() {
        //given
        PageRequest pageRequest = PageRequest.of(0, 20,
            Sort.by(Sort.Direction.ASC, "dateTime.startTime"));

        //when
        List<Performance> performances = performanceQueryService.findPerformancesOrderByStartTimeAsc(
            pageRequest);

        //then
        assertThat(performances.size()).isEqualTo(2);
    }
}
