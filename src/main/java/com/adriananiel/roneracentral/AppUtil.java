package com.adriananiel.roneracentral;

public class AppUtil {
    private static String loggedInUser;

    public static void setLoggedInUser(String user) {
        loggedInUser = user;
    }

    public static String getLoggedInUser() {
        return loggedInUser;
    }
}
