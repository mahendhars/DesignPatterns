package com.behavioral.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.behavioral.command.BankAccountCommand.Action;

class BankAccount {
	private int balance;
	private int overdraftLimit = -500;
	
	public boolean deposit(int amount) {
		balance += amount;
		System.out.println("Deposited " + amount + ", balance is now " + balance);
		return true;
	}
	
	public boolean withdraw(int amount) {
		if (balance - amount >= overdraftLimit) {
			balance -= amount;
			System.out.println("Withdrew " + amount + ", balance is now " + balance);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "BankAccount [balance=" + balance + "]";
	}
}

interface Command {
	void call();
	void undo();
}

class BankAccountCommand implements Command {
	
	enum Action {
		DEPOSIT, WITHDRAW;
	}
	
	private BankAccount bankAccount;
	private Action action;
	private boolean succeeded;
	private int amount;
	
	public BankAccountCommand(BankAccount bankAccount, Action action, int amount) {
		this.bankAccount = bankAccount;
		this.action = action;
		this.amount = amount;
	}

	@Override
	public void call() {
		switch (action) {
		case DEPOSIT:
			succeeded = bankAccount.deposit(amount);
			break;
		case WITHDRAW:
			succeeded = bankAccount.withdraw(amount);
			break;
		}
	}

	@Override
	public void undo() {
		if(!succeeded) return;
		switch (action) {
		case DEPOSIT:
			bankAccount.withdraw(amount);
			break;
		case WITHDRAW:
			bankAccount.deposit(amount);
			break;
		}
	}
}

public class CommandDemo {
	
	public static void main(String[] args) {
		BankAccount ba = new BankAccount();
		System.out.println(ba);
		
		List<BankAccountCommand> commands = Arrays.asList(
				new BankAccountCommand(ba, Action.DEPOSIT, 100),
				new BankAccountCommand(ba, Action.WITHDRAW, 1000)
				);
		
		for(BankAccountCommand command : commands) {
			command.call();
			System.out.println(ba);
		}
		
		Collections.reverse(commands);
		
		//undoing the operation
		for(Command command : commands) {
			command.undo();
			System.out.println(ba);
		}
	}
}
