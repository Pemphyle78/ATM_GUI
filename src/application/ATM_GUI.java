package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ATM_GUI extends Application {
	//Stage loginStage;
	Scene login, applicationScene, checkBalanceScene, makeDepositScene, makeWithdrawalScene;
	String accountNumber = "";
	String actBalance = "";
	String depositAmount = "";
	double withdrawAmount = 0.0;
	Label accountBalance = null;
	
	Account currentAccount = null;
	@Override
	public void start(Stage primaryStage) {
		
		try {

			primaryStage.setTitle("Pemphile ATM Systems");

			GridPane titlePane = new GridPane();
			GridPane pane = new GridPane();
			
			VBox items = new VBox();
			HBox vbButtons = new HBox();
			items.setStyle("-fx-background-color: beige");

			//pane.setPadding(new Insets(5, 5, 5, 5));
			pane.setHgap(10);
			pane.setVgap(10);
			pane.setAlignment(Pos.CENTER);
			
			Label appTitle = new Label("Pemphile ATM Systems Inc.");
			appTitle.setStyle("-fx-font-weight: bold");
			appTitle.setFont(new Font("Cambria", 25));

			Label usernameLbl = new Label("Username");
			Label passwordLbl = new Label("Password");
			TextField usernameTf = new TextField();
			PasswordField passwordTf = new PasswordField();

			Label message = new Label("");
			message.setTextFill(Color.web("#ff0000"));
			message.setVisible(false);

			Button loginBtn = new Button("Login");
			Button cancelBtn = new Button("Cancel");
			Button exitBtn = new Button("Exit");

			titlePane.add(appTitle, 1, 10);
			titlePane.setAlignment(Pos.CENTER);
			titlePane.setVgap(15);
			
			vbButtons.setSpacing(10);
			vbButtons.setPadding(new Insets(0, 20, 10, 20));
			vbButtons.getChildren().addAll(loginBtn, cancelBtn, exitBtn);
			
			pane.add(usernameLbl, 0, 0);
			pane.add(passwordLbl, 0, 1);
			pane.add(usernameTf, 1, 0);
			pane.add(passwordTf, 1, 1);
			pane.add(vbButtons, 1, 2);
			pane.add(message, 1, 3);

			loginBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
//			        
					if (usernameTf.getText().isEmpty() || passwordTf.getText().isEmpty()) {
						message.setText("Username/Password cannot be empty.");
						message.setVisible(true);
						System.out.println("Username/Password cannot be empty.");
					} else {
						System.out.println("Fields were not empty.");
						System.out.println("Login was successful");
						
						Functionality f1 = new Functionality();
						
						currentAccount = f1.login(usernameTf.getText(), passwordTf.getText());
						accountNumber = currentAccount.card;
						actBalance = Double.toString(currentAccount.getBalance());
						System.out.println(currentAccount.toString());
						
						formElement(primaryStage);
						
						primaryStage.setScene(applicationScene);
						
					}
				}
			});
			
			cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					message.setVisible(false);
					usernameTf.clear();
					passwordTf.clear();
					System.out.println("Fields were cleared.");
				}
			});
			
			exitBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					primaryStage.close();
					System.out.println("Closed application.");
				}
			});
						
			items.getChildren().addAll(titlePane, pane);			
			
			Scene loginScene = new Scene(items, 400, 400);
			
			
//			formElement(primaryStage);
			
			depositTransaction(primaryStage);
			
			// Withdrawal Scene begin
			VBox withdraw = new VBox();
			withdraw.setStyle("-fx-background-color: beige");
			HBox withdrawHbox = new HBox();
			
			makeWithdrawalScene = new Scene(withdraw, 400, 400);
			withdraw.setSpacing(20);
			Label lable = new Label("Deposit Money");
			lable.setFont(new Font("Cambria", 20));
			lable.setStyle("-fx-font-weight: bold");
			Label label3 = new Label("How much do you want to withdraw?");
			TextField withdrawalAmountTf = new TextField();			
			
			Button confirmWBtn = new Button("Confirm");
			Button cancelWBtn = new Button("Cancel");			
			
			withdrawHbox.setSpacing(10);
			withdrawHbox.setPadding(new Insets(0, 20, 10, 20));
			withdrawHbox.getChildren().addAll(confirmWBtn, cancelWBtn);
			
			withdraw.setPadding(new Insets(0, 20, 10, 20));
			withdraw.getChildren().addAll(lable, label3, withdrawalAmountTf, withdrawHbox);
			
			cancelWBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					primaryStage.setScene(applicationScene);
				}
				
			});
			
			confirmWBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					
					depositAmount = withdrawalAmountTf.getText();
					if (depositAmount != null) {
						System.out.println(depositAmount);
						double amount = Double.parseDouble(depositAmount);
						System.out.println(amount);
						if (amount > 0) {
							currentAccount.withdraw(amount);
							System.out.println(currentAccount.getBalance());
							actBalance = Double.toString(currentAccount.getBalance());
//							Label accountBalance = new Label(actBalance);
							accountBalance.setText(actBalance);
							System.out.println(actBalance);
						}
					}
					
					
					primaryStage.setScene(applicationScene);
				}
				
			});
			//End
			
			primaryStage.setResizable(false);
			// Setting and showing primary Scene
			primaryStage.setScene(loginScene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void depositTransaction(Stage primaryStage) {
		// Deposit Scene begin
		VBox dep = new VBox();
		dep.setStyle("-fx-background-color: beige");
		HBox depHbox = new HBox();
		
		makeDepositScene = new Scene(dep, 400, 400);
		dep.setSpacing(20);
		Label labl = new Label("Deposit Money");
		labl.setFont(new Font("Cambria", 20));
		labl.setStyle("-fx-font-weight: bold");
		Label label2 = new Label("How much do you want to deposit?");
		TextField depositAmountTf = new TextField();
				
		Button confirm = new Button("Confirm");			
		Button cancel = new Button("Cancel");			
		
		depHbox.setSpacing(10);
		depHbox.setPadding(new Insets(0, 20, 10, 20));
		depHbox.getChildren().addAll(confirm, cancel);
		
		dep.setPadding(new Insets(0, 20, 10, 20));
		dep.getChildren().addAll(labl, label2, depositAmountTf, depHbox);
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				primaryStage.setScene(applicationScene);
			}
			
		});
		confirm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				depositAmount = depositAmountTf.getText();
				if (depositAmount != null) {
					System.out.println(depositAmount);
					double amount = Double.parseDouble(depositAmount);
					System.out.println(amount);
					if (amount > 0) {
						currentAccount.deposit(amount);
						System.out.println(currentAccount.getBalance());
						actBalance = Double.toString(currentAccount.getBalance());
//						Label accountBalance = new Label(actBalance);
						accountBalance.setText(actBalance);
						System.out.println(actBalance);
					}
				}
				
				
				primaryStage.setScene(applicationScene);
			}
			
		});
		//End
	}

	public void formElement(Stage primaryStage) {
		// Application Scene setup begin
		VBox appGroup = new VBox();
		appGroup.setStyle("-fx-background-color: beige");
		HBox cardNumber = new HBox();
		
		appGroup.setSpacing(30);
		applicationScene = new Scene(appGroup, 400, 400);
		
		appGroup.setAlignment(Pos.CENTER);
		
		Label l1 = new Label("Welcome to Pemphile ATM Systems, Inc");
		l1.setFont(new Font("Cambria", 18));
		l1.setStyle("-fx-font-weight: bold");
		
		Label l2 = new Label("Your Card Number is: ");
		Label cardNumberLbl = new Label(accountNumber);
		Label l3 = new Label("Please choose an option:");
		
		Button checkBalanceBtn = new Button("Check Balance");
		checkBalanceBtn.setMaxWidth(Double.MAX_VALUE);
		checkBalanceBtn.setStyle("-fx-background-color: tan ");
		Button makeDepositBtn = new Button("Make a Deposit");
		makeDepositBtn.setStyle("-fx-background-color: tan ");
		makeDepositBtn.setMaxWidth(Double.MAX_VALUE);
		Button withdrawalBtn = new Button("Withdrawal");
		withdrawalBtn.setStyle("-fx-background-color: tan ");
		withdrawalBtn.setMaxWidth(Double.MAX_VALUE);
		Button exitAppBtn = new Button("Exit/Logout");
		exitAppBtn.setStyle("-fx-background-color: tomato ");
		exitAppBtn.setMaxWidth(Double.MAX_VALUE);
		
		cardNumber.getChildren().addAll(l2, cardNumberLbl);
		appGroup.setSpacing(10);
		appGroup.setPadding(new Insets(0, 20, 10, 20));
		appGroup.getChildren().addAll(l1, cardNumber, l3, checkBalanceBtn, makeDepositBtn, withdrawalBtn, exitAppBtn);
		
		checkBalanceBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				primaryStage.setScene(checkBalanceScene);
			}
			
		});
		
		makeDepositBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				primaryStage.setScene(makeDepositScene);
			}
			
		});
		
		withdrawalBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				primaryStage.setScene(makeWithdrawalScene);
			}
			
		});
		
		exitAppBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				primaryStage.close();
				
			}
			
		});
		
		//End
		
		// Check Balance Scene begin
		VBox vbox = new VBox();
		vbox.setStyle("-fx-background-color: beige");
		
		checkBalanceScene = new Scene(vbox, 400, 400);
		vbox.setSpacing(20);
					
		Label label0 = new Label("Balance Inquiry");
		label0.setFont(new Font("Cambria", 20));
		label0.setStyle("-fx-font-weight: bold");
		Label label1 = new Label("Your Account Balance is:");
		accountBalance = new Label(actBalance);
		
		Button close = new Button("Close");			
		
		vbox.setPadding(new Insets(0, 20, 10, 20));
		vbox.getChildren().addAll(label0, label1, accountBalance, close);
		
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				primaryStage.setScene(applicationScene);
			}
			
		});
		
		//End
	}

	public static void main(String[] args) {
		launch(args);
	}
}
