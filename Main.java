import java.util.Scanner;
import java.text.NumberFormat;
import java.lang.Math;

public class Main {

    public static String getInput(String statement){
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.print(statement);
        input = scanner.next();
        return input;
    }

    public static Double calculateMonthlyInterest(Double annualInterest){
        final Byte PERCENT = 100;
        final Byte MONTHS_IN_YEAR = 12;
        Double monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;

        return monthlyInterest;
    }

    public static Double calculateTermInMonths(Double termInYears){
        final Byte MONTHS_IN_YEAR = 12;

        Double termInMonths = termInYears * MONTHS_IN_YEAR;

        return termInMonths;
    }

    public static String calculateMonthlyMortgagePayments(Double principle, Double monthlyInterest, Double termInMonths){
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        Double monthlyMortgagePayment = principle * ( monthlyInterest * Math.pow(1 + monthlyInterest, termInMonths) / ( Math.pow(1 + monthlyInterest, termInMonths) -1 ) ) ;
        String monthlyMortgagePaymentFormatted = currency.format(monthlyMortgagePayment);
        return monthlyMortgagePaymentFormatted;
    }
    public static void main(String[] args) {
        // Set user input question statements
        String principleStatement = "Enter the principle loan amount ($1k - $1M): ";
        String interestStatement = "Enter the annual interest rate (1 - 30): ";
        String termStatement = "Enter the mortgage term in years (1 - 35): ";
        
        // Initialse variables
        Double principle = 0.0;
        Double termInMonths = 0.0;
        Double termInYears = 0.0;
        Double monthlyInterest = 0.0;
        Double annualInterest = 0.0;

        while (true){
            principle = Double.parseDouble(getInput(principleStatement));
            if (principle >= 1_000 && principle <= 1_000_000)
                break;
        }

        while (true){
            termInYears = Double.parseDouble(getInput(termStatement));
            if (termInYears > 0.0D && termInYears <=35.0D) {
                termInMonths = calculateTermInMonths(termInYears);
                break;
            }
        }

        while (true){
            annualInterest = Double.parseDouble(getInput(interestStatement));
            if (annualInterest > 0.0F && annualInterest <= 30.0F){
                monthlyInterest = calculateMonthlyInterest(annualInterest);
                break;
            }
        }

        System.out.println(calculateMonthlyMortgagePayments(principle, monthlyInterest, termInMonths));
    }
}