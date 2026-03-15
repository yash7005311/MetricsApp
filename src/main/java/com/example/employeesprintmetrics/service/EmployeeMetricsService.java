package com.example.employeesprintmetrics.service;

import com.example.employeesprintmetrics.dto.EmployeeMetricsRequest;
import com.example.employeesprintmetrics.dto.EmployeeMetricsResponse;
import com.example.employeesprintmetrics.entity.EmployeeMetricsEntity;
import com.example.employeesprintmetrics.entity.Sprint;
import com.example.employeesprintmetrics.repository.EmployeeMetricsRepository;
import com.example.employeesprintmetrics.repository.SprintRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeMetricsService {

    private final EmployeeMetricsRepository metricsRepository;
    private final SprintRepository sprintRepository;

    public EmployeeMetricsService(EmployeeMetricsRepository metricsRepository,
                                  SprintRepository sprintRepository) {
        this.metricsRepository = metricsRepository;
        this.sprintRepository = sprintRepository;
    }

    public EmployeeMetricsResponse submitMetrics(EmployeeMetricsRequest request) {
        Sprint sprint = sprintRepository.findById(request.getSprintId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sprint not found"));

        EmployeeMetricsEntity entity = metricsRepository
                .findBySprintIdAndEmployeeId(request.getSprintId(), request.getEmployeeId())
                .orElse(new EmployeeMetricsEntity());

        entity.setSprint(sprint);
        entity.setEmployeeId(request.getEmployeeId());
        entity.setPointsCommitted(request.getPointsCommitted());
        entity.setPointsDelivered(request.getPointsDelivered());
        entity.setBugsCount(request.getBugsCount());
        entity.setPullRequestCount(request.getPullRequestCount());
        entity.setSpilloverPoints(request.getSpilloverPoints());
        entity.setSpilloverReason(request.getSpilloverReason());

        entity = metricsRepository.save(entity);
        return EmployeeMetricsResponse.from(entity);
    }

    @Transactional(readOnly = true)
    public List<EmployeeMetricsResponse> getMetricsBySprint(Long sprintId) {
        return metricsRepository.findBySprintIdOrderByEmployeeId(sprintId).stream()
                .map(EmployeeMetricsResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmployeeMetricsResponse getMetricsForEmployee(Long sprintId, String employeeId) {
        return metricsRepository.findBySprintIdAndEmployeeId(sprintId, employeeId)
                .map(EmployeeMetricsResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Metrics not found"));
    }
}
