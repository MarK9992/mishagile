package ui;

import user.UserAccount;
import user.UserManager;

import java.util.Scanner;

/**
 * Created by root on 11/10/14.
 */
public class LoginUi {

    private Scanner sc;
    private UserManager um;

    // Constructor
    public LoginUi(UserManager um){
        sc = new Scanner(System.in);
        this.um = um;
        display();
    }

    public LoginUi(){
        this(new UserManager());
    }

    // Methods
    public String getLogin(){
        System.out.print("LOGIN: ");
        return sc.nextLine();
    }

    public String getPassword(){
        System.out.print("PASSWORD: ");
        return sc.nextLine();
    }

    public void display(){
        System.out.println("Welcome to INSERT METAPHOR\n\nPlease enter your login and password.\n");

        UserAccount ua;

        while((ua = um.login(getLogin(),getPassword())) == null){
            System.out.println("LOGIN and/or PASSWORD incorrect. Please try again.\n");
        }

        MenuUi mui = new MenuUi(ua);
    }
}
