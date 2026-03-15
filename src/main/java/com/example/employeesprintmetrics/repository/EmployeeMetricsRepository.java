package com.example.employeesprintmetrics.repository;

import com.example.employeesprintmetrics.entity.EmployeeMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeMetricsRepository extends JpaRepository<EmployeeMetricsEntity, Long> {

    List<EmployeeMetricsEntity> findBySprintIdOrderByEmployeeId(Long sprintId);

    Optional<EmployeeMetricsEntity> findBySprintIdAndEmployeeId(Long sprintId, String employeeId);
}
