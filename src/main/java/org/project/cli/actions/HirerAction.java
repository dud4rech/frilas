package org.project.cli.actions;

import org.project.cli.ActionLineInterface;
import org.project.controller.PaymentController;
import org.project.controller.ProjectController;

import org.project.controller.ProposalController;
import org.project.enums.UserType;
import org.project.model.PaymentModel;
import org.project.model.ProjectModel;
import org.project.model.ProposalModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class HirerAction {

    public static void execute(Connection con) throws SQLException {
        int command;
        boolean hasProjects = false;
        boolean hasPayments = false;
        boolean hasProposals = false;

        do {
            System.out.println("\n1 - Create project");
            System.out.println("2 - Edit project");
            System.out.println("3 - Delete project");
            System.out.println("4 - Choose proposal");
            System.out.println("5 - Make a payment");
            System.out.println("6 - Rating");
            System.out.println("7 - Reports");
            System.out.println("8 - Back to main menu");

            command = Utils.readInt();

            switch (command) {
                case 1:
                    ProjectController.createProject(con);
                    break;
                case 2:
                    hasProjects = ProjectModel.listAllByUser(con);
                    if (hasProjects) ProjectController.updateProject(con);
                    break;
                case 3:
                    hasProjects = ProjectModel.listAllByUser(con);
                    if (hasProjects) ProjectController.deleteProject(con);
                    break;
                case 4:
                    hasProjects = ProjectModel.listAllByUser(con);
                    if (hasProjects) {
                        System.out.println("Enter the project id: ");
                        int projectId = Utils.readInt();

                        hasProposals = ProposalModel.listAllByProject(projectId, con);
                        if (hasProposals) ProposalController.acceptProposal(projectId, con);
                    }
                    break;
                case 5:
                    hasPayments = PaymentModel.listAllByUser(con);
                    if (hasPayments) PaymentController.makePayment(con);
                    break;
                case 6:
                    RatingAction.execute(con);
                    break;
                case 7:
                    ReportAction.execute(UserType.HIRER.getValue(), con);
                    break;
                case 8:
                    ActionLineInterface.execute(con);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (command != 0);
    }
}
