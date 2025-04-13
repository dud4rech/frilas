package org.project.bean;

public class ProposalBean {
    private int proposalId;
    private int proposalValue;
    private String proposalDescription;
    private int freelancerId;
    private int projectId;

    public ProposalBean(int proposalValue, String proposalDescription, int freelancerId, int projectId) {
        this.proposalValue = proposalValue;
        this.proposalDescription = proposalDescription;
        this.freelancerId = freelancerId;
        this.projectId = projectId;
    }

    public ProposalBean(int proposalId, int proposalValue, String proposalDescription) {
        this.proposalId = proposalId;
        this.proposalValue = proposalValue;
        this.proposalDescription = proposalDescription;
    }

    public ProposalBean(int proposalId) {
        this.proposalId = proposalId;
    }

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public int getProposalValue() {
        return proposalValue;
    }

    public void setProposalValue(int proposalValue) {
        this.proposalValue = proposalValue;
    }

    public String getproposalDescription() {
        return proposalDescription;
    }

    public void setproposalDescription(String proposalDescription) {
        this.proposalDescription = proposalDescription;
    }

    public int getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(int freelancerId) {
        this.freelancerId = freelancerId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
