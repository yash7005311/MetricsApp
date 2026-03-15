package com.example.employeesprintmetrics.controller;

import com.example.employeesprintmetrics.dto.EmployeeMetricsRequest;
import com.example.employeesprintmetrics.dto.EmployeeMetricsResponse;
import com.example.employeesprintmetrics.service.EmployeeMetricsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
@CrossOrigin
public class EmployeeMetricsController {

    private final EmployeeMetricsService metricsService;

    public EmployeeMetricsController(EmployeeMetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @PostMapping
    public ResponseEntity<EmployeeMetricsResponse> submitMetrics(@Valid @RequestBody EmployeeMetricsRequest request) {
        EmployeeMetricsResponse response = metricsService.submitMetrics(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<EmployeeMetricsResponse> getMetrics(@RequestParam(required = true) Long sprintId) {
        return metricsService.getMetricsBySprint(sprintId);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeMetricsResponse> getMetricsForEmployee(
            @RequestParam Long sprintId,
            @PathVariable String employeeId) {
        return ResponseEntity.ok(metricsService.getMetricsForEmployee(sprintId, employeeId));
    }
}
