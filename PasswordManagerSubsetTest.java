//import org.junit.Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordManagerSubsetTest {
    @Test
    void testPasswordManager1() {
        PasswordManager evaluator = new PasswordManager("secret", 5);
        assertEquals(5, evaluator.getSize());
    }

    @Test
    void testGeneratePwd2() {
        PasswordManager evaluator = new PasswordManager("secret", 5);
        assertEquals("HX75Gap]VDz6l", evaluator.generatePwd(1032L));
    }

    @Test
    void testHashPwd2() {
        PasswordManager evaluator = new PasswordManager("secret", 11);
        assertEquals(36248145297568L, evaluator.hashPwd("wNim\\cnw=3J="));
    }

    @Test
    void testHashIndex2() {
        PasswordManager evaluator = new PasswordManager("secret", 11);
        assertEquals(6, evaluator.hashIndex(36248145297568L, new Account()));
    }
    @Test
    public void testHashIndex4() {
        PasswordManager evaluator = new PasswordManager("secret", 5);
        Account a4 = new Account("Uber", "jdoe123");
        evaluator.addAccount(a4, 13L);
        Account a5 = new Account("Mint", "jdoe123");
        evaluator.addAccount(a5, 13L);
        //This is the generated password using a seed of 13L - should wrap around to 0
        assertEquals(0, evaluator.hashIndex(evaluator.hashPwd("Li4aV3C0\\8FCS8Jp5"), new Account()));
    }
    @Test
    public void testAddAccount2() {
        PasswordManager evaluator = new PasswordManager("secret", 5);
        Account a3 = new Account("Walmart", "jdoe123");
        evaluator.addAccount(a3, 52L);
        assertEquals("0: : \n1: : \n2: : \n3: Walmart: C75hWY:hA]S[13LMl\n4: : \n", evaluator.toString());
    }

    @Test
    public void testAddAccount3() {
        PasswordManager evaluator = new PasswordManager("secret", 5);
        Account a2 = new Account("Overstock", "jdoe123");
        evaluator.addAccount(a2, 52L);
        Account a3 = new Account("Walmart", "jdoe123");
        evaluator.addAccount(a3, 52L);
        assertEquals("0: : \n1: : \n2: : \n3: Overstock: C75hWY:hA]S[13LMl\n4: Walmart: C75hWY:hA]S[13LMl\n", evaluator.toString());
    }
    @Test
    public void testUpdatePwd1() {
        PasswordManager evaluator = new PasswordManager("secret", 5);
        Account a3 = new Account("Walmart", "jdoe123");
        evaluator.addAccount(a3, 52L);
        evaluator.updatePwd(a3, 24);
        assertEquals("0: : \n1: : \n2: Walmart: XcHSF2X9T`>>[pPAC`ofa\n3: : deleted\n4: : \n", evaluator.toString());
    }

    @Test
    public void testUpdatePwd2() {
        PasswordManager evaluator = new PasswordManager("secret", 5);
        Account a3 = new Account("Walmart", "jdoe123");
        evaluator.addAccount(a3, 52L);
        evaluator.updatePwd(a3, 23);
        assertEquals("0: : \n1: : \n2: : \n3: Walmart: GQxBBXSO]uU>D\n4: : \n", evaluator.toString());
    }

    @Test
    public void testUpdatePwd4() {
        PasswordManager evaluator = new PasswordManager("secret", 5);
        Account a2 = new Account("Overstock", "jdoe123");
        evaluator.addAccount(a2, 52L);
        Account a3 = new Account("Walmart", "jdoe123");
        evaluator.addAccount(a3, 52L);
        evaluator.updatePwd(a3, 23);
        assertEquals("0: : \n1: : \n2: : \n3: Overstock: C75hWY:hA]S[13LMl\n4: Walmart: GQxBBXSO]uU>D\n", evaluator.toString());
    }

    @Test
    public void testUpdatePwd5() {
        PasswordManager evaluator = new PasswordManager("secret", 5);
        Account a2 = new Account("Overstock", "jdoe123");
        evaluator.addAccount(a2, 52L);
        Account a3 = new Account("Walmart", "jdoe123");
        evaluator.addAccount(a3, 52L);
        Account a4 = new Account("Uber", "jdoe123");
        evaluator.addAccount(a4, 52L);
        evaluator.updatePwd(a3, 23);
        assertEquals("0: Uber: C75hWY:hA]S[13LMl\n1: : \n2: : \n3: Overstock: C75hWY:hA]S[13LMl\n4: Walmart: GQxBBXSO]uU>D\n", evaluator.toString());
    }

    @Test
    public void testUpdatePwd8() {
        assertThrows(InvalidPasswordException.class, () -> {
            PasswordManager evaluator = new PasswordManager("secret", 7);
            Account a1 = new Account("Amazon", "jdoe123");
            evaluator.addAccount(a1, 3L);
            Account a2 = new Account("Overstock", "jdoe123");
            evaluator.addAccount(a2, 52L);
            Account a3 = new Account("Walmart", "jdoe123");
            evaluator.addAccount(a3, 52L);
            Account a4 = new Account("Uber", "jdoe123");
            evaluator.addAccount(a4, 13L);
            Account a5 = new Account("Mint", "jdoe123");

            evaluator.updatePwd(a5, 23L);
        });
    }
}