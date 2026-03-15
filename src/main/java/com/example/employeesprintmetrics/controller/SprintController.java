package com.example.employeesprintmetrics.controller;

import com.example.employeesprintmetrics.dto.SprintDto;
import com.example.employeesprintmetrics.entity.Sprint;
import com.example.employeesprintmetrics.repository.SprintRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sprints")
@CrossOrigin
public class SprintController {

    private final SprintRepository sprintRepository;

    public SprintController(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @GetMapping
    public List<SprintDto> listSprints() {
        return sprintRepository.findAllByOrderByStartDateDesc().stream()
                .map(SprintDto::from)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SprintDto createSprint(@RequestBody Map<String, Object> body) {
        String name = (String) body.get("name");
        String startStr = (String) body.get("startDate");
        String endStr = (String) body.get("endDate");
        if (name == null || startStr == null || endStr == null) {
            throw new IllegalArgumentException("name, startDate, endDate required");
        }
        Sprint s = new Sprint();
        s.setName(name);
        s.setStartDate(LocalDate.parse(startStr));
        s.setEndDate(LocalDate.parse(endStr));
        s = sprintRepository.save(s);
        return SprintDto.from(s);
    }
}
