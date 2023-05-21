// CSCI 211
// Brady Moore
// Student ID 10674564
// Program 4
// In keeping with the UM Honor Code, I have neither given nor received assistance from anyone other
// than the instructor.


import java.util.ArrayList;
import java.util.Random;

public class PasswordManager implements PasswordManagerADT {
    private String masterPassword;
    private int size;
    private ArrayList<Account> apps;
    private int numApps;

   /**
     * constructor
     * @param password
     * @param size
     */

    public PasswordManager(String password, int size) {
	// TODO: Initialize instance variables
	// If size is less than 1 throw an Exception
        apps = new ArrayList<Account>(size);
        this.size = size;

        for(int i = 0; i < this.size; i++){
            this.apps.add(new Account());
        }

        this.masterPassword = password;
        this.numApps = size;


        if(size < 1){
            throw new InvalidPasswordException("Size is greater than 1");
        }


    }

    /**
     * method: generatePwd
     * @param seed
     * @return
     */
    public String generatePwd(long seed) {
        // TODO: First, generate a random password length from 6 to 25 (recall that Random's nextInt(n) goe from 0 up to but not including n)
        // Now, generate a random password that is this length
        //      randomly generate one character at a time, again using Random's nextInt
        //      use the ASCII numbers for this, i.e., ASCII 0 (the number 0) to ASCII z (and every character in between) - this is why you should use Random's nextInt
        //      cast the random int to a char
        //      append the char to output
        String output = "";

        int length;
        Random random = new Random(seed);
        int start = 6;
        int end = 25;

        length = random.nextInt((end - start) + 1) + start;
        for(int i = 0; i < length; i ++) {
            char c = (char) (random.nextInt(75)+48);
            output += c + "";
        }


		return output;
    }

    /**
     * method: hashPwd
     * @param pwd
     * @return
     */
    public long hashPwd(String pwd) {
        //TODO: Calculate the hash code by multiplying each character by the base ^ place value, using the ArrayList size (i.e., this.size) as the base
        // For example, if the pwd is "try" and the size of the ArrayList is 7 (i.e., this.size = 7)
        // Then hashCode = 't'*(7^2) + 'r'*(7^1) + 'y'*(7^0)
        // Recall that Java will seamlessly use the ASCII values for 't', 'r' and 'y' since you are multiplying (char gets promoted to int)
        // You will need to cast your result as long
        long hashCode = 0L;


        for(int i = 0; i < pwd.length(); i++){
//
            hashCode += (long) (pwd.charAt(i) * Math.pow(this.size, pwd.length() - (i +1)));

        }


        return hashCode;
    }

    /**
     * method: hashIndex
     * @param hashCode
     * @param a
     * @return
     */
    public int hashIndex(long hashCode, Account a) {
        //TODO: Determine the index using the hash code for the password - recall that you must take the modulus of the size to get the index
        //      Use linear probing to handle collisions
        //      Recall that an index is open if it is empty or "deleted" (it is possible to have a "deleted" entry because of updatePwd)
        //      Hint 1: For updatePwd, it is possible that the Account object's new index is the same as its old - this is a special case to handle
        //      Hint 2: index = (index + 1) % this.size will automatically wrap around to index 0 when you are at the end of your ArrayList - the second term, % this.size, does the wrapping
        //      Comment:  For addAccount, only the hashCode parameter is needed so simply use the no-arg constructor for the Account parameter (i.e., new Account()) - it is a dummy parameter)
        //                For updatePwd, however, you will need to consider the Account instance variables so be sure you use the Account object to be updated as your Account parameter


        int index = (int) (hashCode % size);
        for(int i = 0; i < this.size; i++) {

            if (index == this.size) {
                index = 0;

            }
            if (apps.get(index).getPassword().equals("deleted") || apps.get(index).getUsername().equals("")) {

                return index;
            }
            else {

                System.out.println("Collision at " + index);
                index++;
            }
        }


        return index;


    }

    /**
     * method: addAccount
     * @param a
     * @param seed
     */
    public void addAccount(Account a, long seed) {
        //TODO: Generate a password using the seed
        //      Hash the password and determine the hash index
        //      Add to the apps ArrayList at this index
        //      Increment numApps, the number of apps

        a = new Account(a.getCompany(),a.getUsername());


        String password = generatePwd(seed);
        a.setPassword(password);
        long hashedCode = hashPwd(password);


            int index = hashIndex(hashedCode, a);
            this.apps.set(index, a);

            numApps++;

    }

    /**
     * method: updatePwd (return new password)
     * @param a
     * @param seed
     * @return
     */
    public String updatePwd(Account a, long seed) {
        //TODO: Update the account information, using the seed parameter to calculate a new password, new hash code and new index
        //      Throw an Exception if Account a is not already in the ArrayList
        //      Determining if the Account is in ArrayList apps should NOT be done linearly from the beginning of the list (index 0) - this is extremely inefficient
        //      Instead, begin looking at the current index of Account a, before it is updated.
        //           You have Account a's current password, so use it to get the current index
        //           This should be your starting index for determining if Account a is in your ArrayList apps
        //           Set the old company and username to "" and the old password to "deleted"
        //           HINT 1: Keep in mind that future additions to ArrayList apps can be inserted if the password equals "deleted"
        //           HINT 2: If the new index is the same as the old index, this is a special case to handle

        String pwd = "";

        for(int i = 0; i < this.size; i++) {


            pwd = generatePwd(seed);

            long oldHashCode = hashPwd(pwd);
            long newHashCode = hashPwd(pwd);

            int index = hashIndex(oldHashCode, a);
            int newIndex = hashIndex(newHashCode, a);

            apps.set(index, a);

            if(apps.indexOf(a) == index){
                a.setCompany("");
                a.setUsername("");
                a.setPassword("deleted");


            }

            if(apps.indexOf(a) != index){
                throw new InvalidPasswordException();
            }
        }
		
        return pwd;
    }

    /**
     * method: getSize used for unit testing
     * @return
     */
    public int getSize() {
        return this.size;
    }

    /**
     * method: toString used for unit testing
     *         NOTE: if an index is empty then i: : \n is output (i replaced with the current for-loop value of i)
     * @return
     */
    public String toString() {
        String output = "";
        for(int i = 0; i < this.apps.size(); i++) {
            output += i + ": " + this.apps.get(i) + "\n";
        }
        return output;
    }
}
