package com.self.learnexceptions;

/*The methods of the class implementing IBank must throw the exception specified in the methods of Interface class.  
*/
public interface IBank {
	public int deposit(int amt) throws ApplicationException;
	
	public int withdraw(int amt) throws ApplicationException, InsufficientBalanceException;

}
