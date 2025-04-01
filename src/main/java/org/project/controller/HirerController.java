package org.project.controller;


import org.project.bean.FreelancerBean;
import org.project.bean.HirerBean;
import org.project.model.FreelancerModel;
import org.project.model.HirerModel;
import org.project.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class HirerController {

    public static void createHirer(Connection con) throws SQLException {
        System.out.println("xxxxx:");

        System.out.println("Name: ");
        String hirerName = Utils.readString();

        System.out.println("Phone: ");
        String hirerPhone = Utils.readString();

        System.out.println("E-mail: ");
        String hirerMail = Utils.readString();

        HirerBean hirer = new HirerBean(hirerName, hirerPhone, hirerMail);
        HirerModel.create(hirer, con);

        System.out.println("Success!");
    }
}
