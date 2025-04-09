package org.project.bean;

public class ProjectBean {
    private int projectId;
    private String projectName;
    private String projectDescription;
    private String projectDeadline;
    private int projectBudget;
    private int hirerId;

    public ProjectBean(String projectName, String projectDescription, String projectDeadline, int projectBudget, int hirerId) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectDeadline = projectDeadline;
        this.projectBudget = projectBudget;
        this.hirerId = hirerId;
    }

    public ProjectBean(int projectId, String projectName, String projectDescription, String projectDeadline, int projectBudget) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectDeadline = projectDeadline;
        this.projectBudget = projectBudget;
    }

    public ProjectBean(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projecDescription) {
        this.projectDescription = projecDescription;
    }

    public String getProjectDeadline() {
        return projectDeadline;
    }

    public void setProjectDeadline(String projectDeadline) {
        this.projectDeadline = projectDeadline;
    }

    public int getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(int projectBudget) {
        this.projectBudget = projectBudget;
    }

    public int getHirerId() {
        return hirerId;
    }

    public void setHirerId(int hirerId) {
        this.hirerId = hirerId;
    }
}