package com.example.employeesprintmetrics.config;

import com.example.employeesprintmetrics.entity.Sprint;
import com.example.employeesprintmetrics.repository.SprintRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class SprintDataInitializer {

    @Bean
    CommandLineRunner initSprints(SprintRepository sprintRepository) {
        return args -> {
            if (sprintRepository.count() > 0) return;
            // Create a few sample 2-week sprints
            LocalDate start = LocalDate.now().minusWeeks(6);
            for (int i = 1; i <= 4; i++) {
                Sprint s = new Sprint();
                s.setName("Sprint " + i);
                s.setStartDate(start);
                s.setEndDate(start.plusWeeks(2).minusDays(1));
                sprintRepository.save(s);
                start = start.plusWeeks(2);
            }
        };
    }
}
