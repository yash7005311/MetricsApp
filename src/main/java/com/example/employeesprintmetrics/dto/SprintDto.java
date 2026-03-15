package com.example.employeesprintmetrics.dto;

import com.example.employeesprintmetrics.entity.Sprint;

import java.time.LocalDate;

public class SprintDto {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    public static SprintDto from(Sprint s) {
        SprintDto dto = new SprintDto();
        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setStartDate(s.getStartDate());
        dto.setEndDate(s.getEndDate());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
