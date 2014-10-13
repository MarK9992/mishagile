package user;

import java.util.ArrayList;

/**
 * Created by root on 11/10/14.
 */
// TODO refactor singleton?
public class UserManager {

	// Attributs

	private ArrayList<UserAccount> userList;

	// Constructeurs

	public UserManager() {
		this(new ArrayList<UserAccount>());
	}

	public UserManager(ArrayList<UserAccount> userList) {
		this.userList = userList;
		this.userList.add(new UserAccount());
	}

	// Method

	public UserAccount login(String login, String password) {
		for (UserAccount ua : userList) {
			if (ua.match(login, password)) {
				return ua;
			}
		}
		return null;
	}

	// Accesseurs

	public ArrayList<UserAccount> getUserList() {
		return userList;
	}
}
