package com.example.employeesprintmetrics.dto;

import com.example.employeesprintmetrics.entity.EmployeeMetricsEntity;
import com.example.employeesprintmetrics.entity.Sprint;

public class EmployeeMetricsResponse {

    private Long id;
    private Long sprintId;
    private String sprintName;
    private String employeeId;
    private Integer pointsCommitted;
    private Integer pointsDelivered;
    private Integer bugsCount;
    private Integer pullRequestCount;
    private Integer spilloverPoints;
    private String spilloverReason;

    public static EmployeeMetricsResponse from(EmployeeMetricsEntity entity) {
        EmployeeMetricsResponse r = new EmployeeMetricsResponse();
        r.setId(entity.getId());
        Sprint s = entity.getSprint();
        if (s != null) {
            r.setSprintId(s.getId());
            r.setSprintName(s.getName());
        }
        r.setEmployeeId(entity.getEmployeeId());
        r.setPointsCommitted(entity.getPointsCommitted());
        r.setPointsDelivered(entity.getPointsDelivered());
        r.setBugsCount(entity.getBugsCount());
        r.setPullRequestCount(entity.getPullRequestCount());
        r.setSpilloverPoints(entity.getSpilloverPoints());
        r.setSpilloverReason(entity.getSpilloverReason());
        return r;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
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
