public class PasswordManagerDriver {
    public static void main(String[] args) {
        //Keep the size of the hashmap small for testing purposes
        //I used size 5 but you should consider different sizes such as 7 and 11
        PasswordManager myAccounts = new PasswordManager("secret", 5);

        //Check individual methods for PasswordManager: generatePwd, hashPwd, hashIndex
       //=FoM4kOLyC is the expected password,  156601197 the expected hash code, and 2 is the hash index for a seed of 1
 
        String pwd = myAccounts.generatePwd(47L);
        System.out.println("Password: " + pwd);

        long hashCode = myAccounts.hashPwd(pwd);
        System.out.println("Hash Code: " + hashCode);

        int hashIndex = myAccounts.hashIndex(hashCode, new Account("Amazon", "jdoe123"));
        System.out.println("Hash Index: " + hashIndex);

/*
        Expected results for the given seeds when size of MyAccounts is 5
        0: Overstock: E_CSDAyqcUm`IfB<
        1: Walmart: E_CSDAyqcUm`IfB<
        2: Amazon: X2C40KLw9s`QPYZxgTk
        3: Uber: Li4aV3C0\8FCS8Jp5
        4: Mint: B<@pqZq\_KMs
*/
        // Now create 5 Account objects. Try several combinations of the 5
        // Such as adding 1 object, adding 2 objects that have the same index, etc.
        Account a1 = new Account("Amazon", "jdoe123");
        myAccounts.addAccount(a1, 3L);

        Account a2 = new Account("Overstock", "jdoe123");
        myAccounts.addAccount(a2, 52L);

        Account a3 = new Account("Walmart", "jdoe123");
        myAccounts.addAccount(a3, 52L);


        Account a4 = new Account("Uber", "jdoe123");
        myAccounts.addAccount(a4, 13L);

        Account a5 = new Account("Mint", "jdoe123");
        myAccounts.addAccount(a5, 5L);



        // Update accounts. Again, consider positions. Update to a different index, to the same index, etc.
          myAccounts.updatePwd(a3, 27L);

        // Output accounts
        System.out.println(myAccounts);

    }
}
