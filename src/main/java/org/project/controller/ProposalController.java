package org.project.controller;

import org.project.bean.ProposalBean;
import org.project.cli.actions.LoginAction;
import org.project.model.ProjectModel;
import org.project.model.ProposalModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

public class ProposalController {

    public static void createProposal(Connection con) throws SQLException {
        System.out.println("\n=== Creating a new proposal ===\n");

        System.out.print("Enter the project id: ");
        int projectId = Utils.readInt();

        System.out.print("Enter the proposal value: ");
        int proposalValue = Utils.readInt();

        System.out.print("Enter the proposal description: ");
        String proposalDescription = Utils.readString();

        int freelancerId = LoginAction.getLoggedUser();

        ProposalBean proposal = new ProposalBean(proposalValue, proposalDescription, freelancerId, projectId);
        ProposalModel.create(proposal, con);

        System.out.println("\nProposal created successfully!");
    }

    public static void updateProposal(Connection con) throws SQLException {
        System.out.println("\n=== Editing a proposal ===\n");

        System.out.print("Enter the proposal id: ");
        int proposalId = Utils.readInt();

        System.out.print("Enter the proposal value: ");
        int proposalValue = Utils.readInt();

        System.out.print("Enter the proposal description: ");
        String proposalDescription = Utils.readString();

        ProposalBean proposal = new ProposalBean(proposalId, proposalValue, proposalDescription);

        int proposalUpdated = ProposalModel.update(proposal, con);

        if (proposalUpdated == 0) {
            System.out.println("\nProposal not edited or it doesn't exist.");
        } else {
            System.out.println("\nProposal edited successfully!");
        }
    }

    public static void deleteProposal(Connection con) throws SQLException {
        System.out.println("\n=== Deleting a proposal===\n");

        System.out.print("Enter the proposalid: ");
        int proposalId = Utils.readInt();

        ProposalBean proposal= new ProposalBean(proposalId);
        int proposalDeleted = ProposalModel.delete(proposal, con);

        if (proposalDeleted == 0) {
            System.out.println("\nProposal not deleted or it doesn't exist.");
        } else {
            System.out.println("\nProposal deleted successfully!");
        }
    }

    public static void acceptProposal(int projectId, Connection con) throws SQLException {
        System.out.println("\n=== Accepting a proposal ===\n");

        System.out.print("Enter the proposal id: ");
        int proposalId = Utils.readInt();

        ProposalBean proposal = new ProposalBean(proposalId);
        int proposalAccepted = ProposalModel.updateStatus(proposal, con);

        System.out.println("\nProposal accepted successfully!\n");

        if (proposalAccepted > -1) {
            ProjectModel.updateStatus(projectId, con);

            Map<String, Object> details = ProposalModel.findProjectAndProposalDetails(proposal.getProposalId(), con);
            PaymentController.createPayment(
                    (int) details.get("freelancerid"),
                    (int) details.get("proposalvalue"),
                    (String) details.get("projectdeadline"),
                    (int) details.get("projectid"),
                    con);
        }
    }
}
