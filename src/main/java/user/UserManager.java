package user;

import model.Manager;

import java.util.ArrayList;

/**
 * Created by root on 11/10/14.
 */
// TODO refactor singleton?
public class UserManager extends Manager<UserAccount> {

    // Constructeurs

    public UserManager() {
        this(new ArrayList<UserAccount>());
    }

    public UserManager(ArrayList<UserAccount> list) {
        this.list = list;
        this.list.add(new UserAccount("acd", "acd", UserRank.ACD));
        this.list.add(new UserAccount("bcd", "bcd", UserRank.BCD));
        this.list.add(new UserAccount("cd", "cd", UserRank.CD));
        this.list.add(new UserAccount("fin", "fin", UserRank.FIN));
    }

    // Method

    public UserAccount login(String login, String password) {
        for (UserAccount ua : list) {
            if (ua.match(login, password)) {
                return ua;
            }
        }
        return null;
    }

    // Accesseurs

    public ArrayList<UserAccount> getList() {
        return list;
    }
}
