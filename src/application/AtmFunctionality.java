package application;
import java.util.Scanner;

public class AtmFunctionality {
	
    public static void atmFunctionalities(Account bankAccount) {
    	
        try (Scanner scanner = new Scanner(System.in)) {
        	
        	// Creating a SWITCH case for different options
			char option = '\0';
			
			do {
			    TestAtmSystem.atmOptions();
			    option = scanner.next().charAt(0);
			    // If user enters an option not available, display a message to them 
			    switch (option) {
			        case 'B':
			            System.out.println("\nYour account balance is: " + bankAccount.getBalance() + "\n");
			            break;
			        case 'D':
			            System.out.print("How much do you want to deposit: ");
			            int amountToDeposit = scanner.nextInt();
			            bankAccount.deposit(amountToDeposit);
			            
			            break;
			        case 'W':
			            System.out.print("How much do you want to withdraw: ");
			            int amountToWithdraw = scanner.nextInt();
			            bankAccount.withdraw(amountToWithdraw);
			            break;
			        case 'X':
			            System.out.println("\nThank you for using this ATM.\n");
			            break;
			        default:
			            System.out.println("Option not available. Please try again\n");
			            break;
			    }
			    // Continue executing as long as user has not chosen 'X'
			} while (option != 'X');
		}
    }
}
