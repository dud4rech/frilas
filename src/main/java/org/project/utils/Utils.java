package org.project.utils;

import java.util.Scanner;

public class Utils {
    public static Scanner scanner = new Scanner(System.in);

    public static int readInt() {
        int input = 0;

        try {
            input = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error:" + e);
        } finally {
            clearBuffer();
        }

        return input;
    }

    public static String readString() {
        return scanner.nextLine();
    }

    public static void clearBuffer() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
