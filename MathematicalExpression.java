import java.util.*;
public class MathematicalExpression {

    public static long calculateExpression(String expression) {
        if (!isValid(expression)) {
            System.out.println("Not a valid expression");
            return 0;
        }

        Stack<Long> operand = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int i = 0;

        while (i < expression.length()) {
            char currentChar = expression.charAt(i);

            if (Character.isDigit(currentChar)) {
                StringBuilder currentNumber = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    currentNumber.append(expression.charAt(i));
                    i++;
                }
                operand.push(Long.parseLong(currentNumber.toString()));
            } else {
                if (currentChar == ')') {
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        if (!performOperations(operand, operators)) {
                            return 0; // Error in performing operations
                        }
                    }
                    if (!operators.isEmpty() && operators.peek() == '(') {
                        operators.pop(); // Remove '('
                    }
                } else {
                    while (!operators.isEmpty() && checkPriority(currentChar, operators.peek())) {
                        if (!performOperations(operand, operators)) {
                            return 0; // Error in performing operations
                        }
                    }
                    operators.push(currentChar);
                }
                i++;
            }
        }

        while (!operators.isEmpty()) {
            if (!performOperations(operand, operators)) {
                return 0; // Error in performing operations
            }
        }

        return operand.isEmpty() ? 0 : operand.pop();
    }

    public static boolean checkPriority(Character operator1, Character operator2) {
        if (operator2 == '(' || operator2 == ')') {
            return false;
        }
        if ((operator1 == '*' || operator1 == '/' || operator1 == '%') && (operator2 == '+' || operator2 == '-')) {
            return false;
        }
        return true;
    }

    public static boolean performOperations(Stack<Long> operand, Stack<Character> operators) {
        if (operand.size() < 2) {
            System.out.println("Error: Insufficient operands for operation");
            return false;
        }

        long value2 = operand.pop();
        long value1 = operand.pop();
        char operator = operators.pop();
        long result = operations(value1, value2, operator);

        operand.push(result);
        return true;
    }

    public static long operations(long a, long b, Character operator) {
        try {
            switch (operator) {
                case '+':
                    return a + b;
                case '-':
                    return a - b;
                case '*':
                    return a * b;
                case '/':
                    if (b == 0) {
                        System.out.println("Error: Division by zero");
                        return 0;
                    }
                    return a / b;
                case '%':
                    if (b == 0) {
                        System.out.println("Error: Modulo by zero");
                        return 0;
                    }
                    return a % b;
                default:
                    System.out.println("Error: Invalid operator " + operator);
            }
        } catch (ArithmeticException e) {
            System.out.println("Error in operation: " + e.getMessage());
        }
        return 0;
    }

    public static boolean isValid(String expression) {
        if (expression.isEmpty()) {
            return false;
        }

        HashSet<Character> validOperators = new HashSet<>(Arrays.asList('+', '-', '*', '/', '%', '(', ')'));
        Stack<Character> parenthesesStack = new Stack<>();
        boolean lastWasOperator = true;

        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch)) {
                lastWasOperator = false;
            } else if (validOperators.contains(ch)) {
                if (lastWasOperator && ch != '(') {
                    return false; // Consecutive operators or misplaced operators
                }
                lastWasOperator = (ch != ')');
                if (ch == '(') {
                    parenthesesStack.push(ch);
                } else if (ch == ')') {
                    if (parenthesesStack.isEmpty()) {
                        return false; // Unmatched closing parenthesis
                    }
                    parenthesesStack.pop();
                }
            } else {
                return false; // Invalid character
            }
        }

        return parenthesesStack.isEmpty() && !lastWasOperator;
    }
}
