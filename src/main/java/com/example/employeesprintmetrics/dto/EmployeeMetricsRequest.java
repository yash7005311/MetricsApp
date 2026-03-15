package com.example.employeesprintmetrics.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeMetricsRequest {

    @NotNull
    private Long sprintId;

    @NotBlank
    private String employeeId;

    @NotNull
    @Min(0)
    private Integer pointsCommitted;

    @NotNull
    @Min(0)
    private Integer pointsDelivered;

    @NotNull
    @Min(0)
    private Integer bugsCount;

    @NotNull
    @Min(0)
    private Integer pullRequestCount;

    @NotNull
    @Min(0)
    private Integer spilloverPoints;

    private String spilloverReason;

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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
}
