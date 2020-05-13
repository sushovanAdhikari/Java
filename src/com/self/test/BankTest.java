package com.self.test;

import com.self.learnexceptions.ApplicationException;
import com.self.learnexceptions.CheckingAccount;
import com.self.learnexceptions.IBank;
import com.self.learnexceptions.InsufficientBalanceException;
import com.self.learnexceptions.SavingsAccount;

//invoke com.self.learnexceptions from here 

public class BankTest {

	public static void main(String[] args) {

		IBank bankChecking = new CheckingAccount();

		IBank bankSavings = new SavingsAccount();

		try {
			bankChecking.deposit(500);
			bankChecking.withdraw(250);

			bankSavings.deposit(1500);
			bankSavings.withdraw(499);

		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (InsufficientBalanceException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Final Block has executed");
		}

	}

}
