package org.project.controller;

import org.project.bean.FreelancerBean;
import org.project.model.FreelancerModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class FreelancerController {

    public static void createFreelancer(Connection con) throws SQLException {
        String input = Utils.readString();

        System.out.println("xxxxx:");

        System.out.println("Name: ");
        String freelancerName = input;

        System.out.println("Phone: ");
        String freelancerPhone = input;

        System.out.println("E-mail: ");
        String freelancerMail = input;

        FreelancerBean freelancer = new FreelancerBean(freelancerName, freelancerPhone, freelancerMail);
        FreelancerModel.create(freelancer, con);

        System.out.println("Success!");
    }
}
