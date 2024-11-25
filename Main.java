/***
 * This is the main method which involves function calls
 * Owner Name : Gungun Dua
 * Date of creation: 25/11/2024
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        do{
        System.out.println("Enter the mathematical expression: Example (5-3*2+8/4)");
        String expression = input.nextLine();
        try {
            long result = MathematicalExpression.calculateExpression(expression);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Would you like to repeat : (yes/no) ");
            String repeat = input.nextLine();
            if(repeat.equals("no")){
                break;
            }
        } while(true);
    }
}