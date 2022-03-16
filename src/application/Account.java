package application;
public class Account {
	
    String card;
    double balance;
    String username;
    String password;

    // The fields to be used in the application
    public Account(String card, double balance, String username, String password) {
        this.card = card;
        this.balance = balance;
        this.username = username;
        this.password = password;
    }

    //Get balance method
    public double getBalance() {
        return this.balance;
    }
    
    
    
//    public double setBalance(double amount) {
//    	return this.balance + amount;
//    }
    
    public void setBalance(double balance) {
		this.balance = balance;
	}

	//Make a deposit method
    public void deposit(double amount) {
        this.balance = this.balance + amount;
        setBalance(this.balance);
        System.out.println("** You have just deposited: " + amount + "\n");
    }
    
    // Method for make withdrawals
    public void withdraw(double amount) {
    	// Check if user has enough to make a withdrawal
        if (amount <= this.balance) {
            this.balance = this.balance - amount;
            System.out.println("** You have just withdrawn " + amount + " from your account.\n");
            setBalance(this.balance);
        } else {
            System.out.println("You do not have enough fund to make this transaction.\n");
        }
    }

	@Override
	public String toString() {
		return "Account [card=" + card + ", balance=" + balance + ", username=" + username + ", password=" + password
				+ ", toString()=" + super.toString() + "]";
	}
    
    
}

