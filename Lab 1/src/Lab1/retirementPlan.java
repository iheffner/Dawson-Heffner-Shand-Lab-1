package Lab1;

/**
 * @author Lia Dawson
 * @author Ian Heffner
 * @author Michael Shand
 * CISC181 Section 011
 * 
 * This program asks for user input to calculate how much money the user will need to save
 * for retirement based on their needs, and how much they should invest each month into this
 * retirement fund.
 * 
 * The program uses an imported library FinanceLib to perform the accounting calculations.
 * 
 * The six variables needed: remaining working years, return rate on investments,
 * 		years of retirement, required monthly income in retirement, return rate
 * 		on the retirement investments, and monthly SSI in retirement.
 */

import java.util.Scanner;
import org.apache.poi.ss.formula.functions.FinanceLib;

public class retirementPlan {
	public static void main(String[] args) {
		//Creating a new Scanner class to take in user input
		Scanner input = new Scanner(System.in);
		
		//Ask user for all the necessary information
		//Save user input as a double
		System.out.print("How many years are you working? ");
		double workingYears = input.nextInt();
		
		System.out.print("What's your expected average return (percent 0-100) on your investments? ");
		double investReturn = input.nextInt();
		
		System.out.print("How many years of retirement are you planning for? ");
		double retirementYears = input.nextInt();
		
		System.out.print("How much money do you require each month? ");
		double reqIncome = input.nextInt();
		
		System.out.print("What's your expected average interest rate (percent 0-100) in retirement? ");
		double retirementReturn = input.nextInt();
		
		System.out.print("How much do you expect to receive from Social Security each month? ");
		double monthlySSI = input.nextInt();
		
		//Closing the scanner
		input.close();
		
		//Finding the total amount needed at the start of retirement:
		//pv parameters: (rate, num of periods, pmt per period, future value, type)
		//Calculations for the parameters taken from the Lab 1 Excel sheet
		double r = retirementReturn / 1200;
		double n = retirementYears * 12;
		double y = reqIncome - monthlySSI;
		double f = 0;
		boolean t = false;
		
		//FinanceLib pv function used
		//Multiply by negative one for a positive output
		double retirementFund = -1 * FinanceLib.pv(r, n, y, f, t);
		
		//Telling the user the amount
		System.out.print("You need to save $");
		System.out.printf("%.2f", retirementFund);
		System.out.println(" before entering retirement.");
		
		//To find the monthly investment for remaining working years:
		//pmt parameters: (rate, num of periods, present value, future value, type)
		//Calculations for the parameters taken from the Lab 1 Excel sheet
		r = investReturn / 1200;
		n = workingYears * 12;
		double p = 0;
		f = retirementFund;
		//t remains false
		
		//FinanceLib pmt function used
		double paymentAmount = FinanceLib.pmt(r,n,p,f,t);
		
		//Telling the user the monthly payment
		//Multiply payment by negative one for positive output
		System.out.print("Your monthly payment will be $");
		System.out.printf("%.2f",-1*paymentAmount);
		System.out.print(".");
		}
}
