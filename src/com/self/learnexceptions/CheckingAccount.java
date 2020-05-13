package com.self.learnexceptions;

public class CheckingAccount implements IBank {

	int balance = 0;

	public int deposit(int amt) throws ApplicationException {
		try {
			balance += amt;
		} catch (Exception ex) {
			throw ex;
		}
		return balance;
	}

	public int withdraw(int amt) throws InsufficientBalanceException, ApplicationException {
		try {
			if (balance > amt) {
				balance -= amt;
			} else {
				throw new InsufficientBalanceException("Insufficient Balance to withdraw");
			}
		} catch (InsufficientBalanceException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new ApplicationException(ex.getMessage());
		}
		return balance;

	}

}
