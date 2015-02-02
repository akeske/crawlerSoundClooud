package org.jsoup.Main;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by akeske
 */
public class Main {

    public static final String DB_URL = "jdbc:mysql://localhost/";
    public static final String USER = "root";
    public static final String PASS = "";
    public static final String FIRSTURL = "https://soundcloud.com/infinite-flames/followers";

    public static int duplicateUsersCounter = 0;
    public static int newUsersCounter = 0;

    public static ArrayList<UserInfo> users = new ArrayList<UserInfo>();
    public static int counter = 0;
    public static DBConnection db;

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InitGUI();
            }
        });
    }

    public static ArrayList<UserInfo> getUsers() {
        return users;
    }
}


