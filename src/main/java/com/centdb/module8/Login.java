package com.centdb.module8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.centdb.constants.DatabaseConstants;
import com.centdb.util.Hashing;

public class Login {

    String username, password;
    Scanner input;

    public void userLogin() {
        input = new Scanner(System.in);

        System.out.print("Enter Username: ");
        username = input.nextLine();
        System.out.print("Enter Password: ");
        password = input.nextLine();

        validateUser(username, password);

    }

    private void validateUser(String username, String password) {
        String[] userDetail = null;
        try {
            userDetail = getPasswordRelatedToUser(username);
            if (userDetail != null) {
                String storedPassword = (userDetail[1]);
                Hashing hashedPwd = new Hashing(password);
                if (storedPassword.equals(hashedPwd.passwordHashed)) {
                    System.out.println((userDetail[2]));
                    input = new Scanner(System.in);
                    String answer = input.nextLine();
                    if (answer.equals(userDetail[3])) {
                        System.out.println(
                                "\n -------------------------------------- ACCESS GRANTED -------------------------------------- \n");
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.showMainMenu();
                    } else {
                        System.out.println("Access denied. Wrong Answer");
                    }
                } else {
                    System.out.print("Access denied. Wrong Password");
                }
            } else {
                System.out.println("Access denied. User Not Found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String[] getPasswordRelatedToUser(String username) {
        List<String[]> userDetails = new ArrayList<String[]>();
        String eachLine = "";
        String[] userDetail = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(DatabaseConstants.BASE_USER_DIR_PATH));
            while ((eachLine = br.readLine()) != null) {
                userDetails.add(eachLine.split("#"));
            }

            for (String[] user : userDetails) {
                if (user[0].equals(username)) {
                    userDetail = user;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not Found");
        } catch (IOException e) {
            System.err.println("I/O Error");
        }

        if (userDetail[0].equals(username)) {
            return userDetail;
        } else {
            System.out.println("Access denied. User Not Found");
        }
        return null;
    }
}
