public interface PasswordManagerADT {
    public String generatePwd(long seed);
    public long hashPwd(String pwd);
    public int hashIndex(long hashCode, Account a);
    public void addAccount(Account a, long seed);
    public String updatePwd(Account a, long seed);
    }
