public class Account {
    private String company;
    private String username;
    private String password;


    public Account() {
        this.company = "";
        this.username = "";
        this.password = "";
    }

    public Account(String company, String username) {
        this.company = company;
        this.username = username;
        this.password = "";
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return this.company + ": " + this.password;
    }
}
