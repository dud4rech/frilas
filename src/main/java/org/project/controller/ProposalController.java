package org.project.controller;

import org.project.bean.ProposalBean;
import org.project.cli.actions.LoginAction;
import org.project.model.ProposalModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ProposalController {

    public static void createProposal(Connection con) throws SQLException {
        System.out.println("\n=== Creating a new proposal ===\n");

        System.out.print("Enter the project id: ");
        int projectId = Utils.readInt();

        System.out.print("Enter the proposal value: ");
        int proposalValue = Utils.readInt();

        System.out.print("Enter the proposal message: ");
        String proposalMessage = Utils.readString();

        int freelancerId = LoginAction.getLoggedUser();

        ProposalBean proposal = new ProposalBean(proposalValue, proposalMessage, freelancerId, projectId);
        ProposalModel.create(proposal, con);

        System.out.println("\nProposal created successfully!\n");
    }

    public static void updateProposal(Connection con) throws SQLException {
        System.out.println("\n=== Editing a proposal ===\n");

        System.out.print("Enter the proposal id: ");
        int proposalId = Utils.readInt();

        System.out.print("Enter the proposal value: ");
        int proposalValue = Utils.readInt();

        System.out.print("Enter the proposal message: ");
        String proposalMessage = Utils.readString();

        ProposalBean proposal = new ProposalBean(proposalId, proposalValue, proposalMessage);
        ProposalModel.update(proposal, con);

        System.out.println("\nProposal edited successfully!\n");
    }

    public static void deleteProposal(Connection con) throws SQLException {
        System.out.println("\n=== Deleting a proposal===\n");

        System.out.print("Enter the proposalid: ");
        int proposalId = Utils.readInt();

        ProposalBean proposal= new ProposalBean(proposalId);
        ProposalModel.delete(proposal, con);

        System.out.println("\nProposal deleted successfully!\n");
    }

    public static void acceptProposal(Connection con) throws SQLException {
        System.out.println("\n=== Accepting a proposal ===\n");

        System.out.print("Enter the proposal id: ");
        int proposalId = Utils.readInt();

        ProposalBean proposal = new ProposalBean(proposalId);
        ProposalModel.updateStatus(proposal, con);

        System.out.println("\nProposal edited successfully!\n");
    }
}
