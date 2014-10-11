package user;

/**
 * Created by root on 11/10/14.
 */
public class UserAccount {

    // Attributes

    private String login, password;
    private UserRank rank;

    // Constructors

    public UserAccount() {
        this("login", "password", UserRank.CD);
    }

    public UserAccount(String login, String password, UserRank rank) {
        this.login = login;
        this.password = password;
        this.rank = rank;
    }

    // Methods

    public boolean match(String login, String password) {
        if(this.login.equals(login) && this.password.equals(password)) {
            return true;
        }
        return false;
    }

    // Accessors

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRank getRank() {
        return rank;
    }

    // Override

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount)) return false;

        UserAccount that = (UserAccount) o;

        if (!login.equals(that.login)) return false;
        if (!password.equals(that.password)) return false;
        if (rank != that.rank) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }

}
