package com.self.learnexceptions;

/*The class implements IBank where methods throw exception errors */

public class SavingsAccount implements IBank {

	int balance = 0;

	public int deposit(int amt) throws ApplicationException {
		try {
			balance += amt;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw ex;
		}
		return balance;
	}

	public int withdraw(int amt) throws ApplicationException, InsufficientBalanceException {

		try {

			if (balance - amt > 1000) {
				balance -= amt;
			}else {
				throw new InsufficientBalanceException("From inside else block Not Enough Balance");
			}
		}catch(InsufficientBalanceException ex) {
			throw ex;
		}catch (Exception ex) {
			throw new ApplicationException(ex.getMessage());
		}
		return balance;
	}
}
