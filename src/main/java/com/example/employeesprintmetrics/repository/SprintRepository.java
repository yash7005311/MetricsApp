package com.example.employeesprintmetrics.repository;

import com.example.employeesprintmetrics.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SprintRepository extends JpaRepository<Sprint, Long> {

    List<Sprint> findAllByOrderByStartDateDesc();
}
