package org.project.cli.actions;

import org.project.cli.ActionLineInterface;
import org.project.controller.ProposalController;
import org.project.enums.UserType;
import org.project.model.ProjectModel;
import org.project.model.ProposalModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class FreelancerAction {

    public static void execute(Connection con) throws SQLException {
        int command;
        boolean hasProposals = false;
        boolean hasProjects = false;

        do {
            System.out.println("\n1 - Make proposal");
            System.out.println("2 - Edit proposal");
            System.out.println("3 - Delete proposal");
            System.out.println("4 - Reports");
            System.out.println("5 - Back");

            command = Utils.readInt();

            switch (command) {
                case 1:
                    hasProjects = ProjectModel.listAll(con);
                    if (hasProjects) ProposalController.createProposal(con);
                    break;
                case 2:
                    hasProposals = ProposalModel.listAllNotAccepted(con);
                    if (hasProposals) ProposalController.updateProposal(con);
                    break;
                case 3:
                    hasProposals = ProposalModel.listAllNotAccepted(con);
                    if (hasProposals) ProposalController.deleteProposal(con);
                    break;
                case 4:
                    ReportAction.execute(UserType.FREELANCER.getValue(), con);
                    break;
                case 5:
                    ActionLineInterface.execute(con);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (command != 0);
    }
}
