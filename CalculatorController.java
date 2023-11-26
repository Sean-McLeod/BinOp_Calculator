import Model.CalculatorModel;

import java.util.Scanner;

public class CalculatorController
{
    public static void main(String[] args) {
        CalculatorModel model = new CalculatorModel();
        boolean isDone = false;

        Scanner scanner = new Scanner(System.in);

        while (!isDone) {
            System.out.print("Enter Value: ");

            // Read user input
            String userInput = scanner.nextLine();
            if (userInput.equals("DONE")) {
                isDone = true;
            }
            System.out.println(model.ExecuteAction(userInput));

        }
        scanner.close();
    }
}
