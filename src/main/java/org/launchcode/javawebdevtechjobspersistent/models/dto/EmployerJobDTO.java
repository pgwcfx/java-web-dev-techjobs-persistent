package org.launchcode.javawebdevtechjobspersistent.models.dto;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;

import javax.validation.constraints.NotNull;

public class EmployerJobDTO {

    @NotNull
    private Employer employer;

    @NotNull
    private Job job;

    public EmployerJobDTO(){}

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
