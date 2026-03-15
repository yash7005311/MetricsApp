package com.example.employeesprintmetrics.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employee_metrics")
public class EmployeeMetricsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id", nullable = false)
    private Sprint sprint;

    @Column(name = "points_committed", nullable = false)
    private Integer pointsCommitted;

    @Column(name = "points_delivered", nullable = false)
    private Integer pointsDelivered;

    @Column(name = "bugs_count", nullable = false)
    private Integer bugsCount;

    @Column(name = "pull_request_count", nullable = false)
    private Integer pullRequestCount;

    @Column(name = "spillover_points", nullable = false)
    private Integer spilloverPoints;

    @Column(name = "spillover_reason", length = 1000)
    private String spilloverReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Integer getPointsCommitted() {
        return pointsCommitted;
    }

    public void setPointsCommitted(Integer pointsCommitted) {
        this.pointsCommitted = pointsCommitted;
    }

    public Integer getPointsDelivered() {
        return pointsDelivered;
    }

    public void setPointsDelivered(Integer pointsDelivered) {
        this.pointsDelivered = pointsDelivered;
    }

    public Integer getBugsCount() {
        return bugsCount;
    }

    public void setBugsCount(Integer bugsCount) {
        this.bugsCount = bugsCount;
    }

    public Integer getPullRequestCount() {
        return pullRequestCount;
    }

    public void setPullRequestCount(Integer pullRequestCount) {
        this.pullRequestCount = pullRequestCount;
    }

    public Integer getSpilloverPoints() {
        return spilloverPoints;
    }

    public void setSpilloverPoints(Integer spilloverPoints) {
        this.spilloverPoints = spilloverPoints;
    }

    public String getSpilloverReason() {
        return spilloverReason;
    }

    public void setSpilloverReason(String spilloverReason) {
        this.spilloverReason = spilloverReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeMetricsEntity that = (EmployeeMetricsEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
