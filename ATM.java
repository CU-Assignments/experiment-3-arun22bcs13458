import java.util.Scanner;

class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class ATM {
    private static final int CORRECT_PIN = 1234;
    private static double balance = 3000; // Initial balance

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Ask for PIN
            System.out.print("Enter PIN: ");
            int enteredPin = scanner.nextInt();
            
            // Validate PIN
            if (enteredPin != CORRECT_PIN) {
                throw new InvalidPinException("Error: Incorrect PIN.");
            }

            // Ask for withdrawal amount
            System.out.print("Withdraw Amount: ");
            double withdrawAmount = scanner.nextDouble();

            // Validate balance
            if (withdrawAmount > balance) {
                throw new InsufficientBalanceException("Error: Insufficient balance.");
            }

            // Successful withdrawal
            balance -= withdrawAmount;
            System.out.println("Withdrawal successful! Remaining Balance: " + balance);

        } catch (InvalidPinException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter numeric values.");
        } finally {
            System.out.println("Current Balance: " + balance); // Always display balance
            scanner.close();
        }
    }
}
