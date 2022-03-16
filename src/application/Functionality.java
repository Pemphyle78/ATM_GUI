package application;
import java.util.Scanner;

public class Functionality {
	
	// Here we create 3 accounts made of card number, initial balance, user, and password.
    Account card1 = new Account("1234567890123456", 800, "pemphile", "1234");
    Account card2 = new Account("3216549871237530", 600, "david", "6398");
    Account card3 = new Account("6320549871237530", 120, "yahoo", "0236");
    
    // We assign them to an array bankAccount
    Account[] bankAccounts = { card1, card2, card3 };
    
    //private static int attempts = 0;
    // Constructor Initialization
    public Functionality() { }
    
    public Account login(String username, String password) {
        for (Account bankAccount : bankAccounts) {
        	// Log the user only if the username AND password are correct
            if (bankAccount.username.equals(username) && bankAccount.password.equals(password)) {
//                System.out.println();
//                System.out.println("Your Card Number is: " + bankAccount.card);
//                System.out.println();
                
                return bankAccount;
            } 
        }
        
        // Display an error message if username/password is invalid
        System.err.println("Invalid Username/Password. Please try again!!!");
        
        return null;
    }
    
    public static void main(String[] args) {
        
    	
        
        Scanner scanner = new Scanner(System.in);
        
        Account currentBankAccount = null;
        
		/*
		 * while (currentBankAccount == null) { // After 3 unsuccessful attempts, the
		 * user has to restart over attempts++; if (attempts > 3) { System.err.
		 * println("You have entered an Invalid Username/Password 3 times! \nYour account is locked. Please try again later..."
		 * ); System.exit(0); }
		 * 
		 * //We call pemphileAtmSystems() here which is the entry point to the system
		 * TestAtmSystem.pemphileAtmSystems();
		 * 
		 * // User enters username and password String username = scanner.nextLine();
		 * String password = scanner.nextLine();
		 * 
		 * currentBankAccount =
		 * Functionality.getAccountByUsernameAndPassword(bankAccounts, username,
		 * password); }
		 */
        
        AtmFunctionality.atmFunctionalities(currentBankAccount);
        
        //Close scanner when finished
        scanner.close();
    }
    
    // Check the user entered password again the value in the account already in the system
    
}     
