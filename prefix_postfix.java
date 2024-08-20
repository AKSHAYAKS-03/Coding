
import java.util.Stack;

public class Main
{
    public static void main(String[] args) {
        String prefix = "*+AB-CD"; // Example prefix expression
        String postfix = convertPrefixToPostfix(prefix);
        System.out.println("Postfix Expression: " + postfix); // Output: "AB+CD-*"
    }

    public static String convertPrefixToPostfix(String prefix) {
        Stack<String> stack = new Stack<>();

        // Read the prefix expression from right to left
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char ch = prefix.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                // It's an operand, push it onto the stack
                stack.push(String.valueOf(ch));
            } else {
                // It's an operator
                // Pop two operands from the stack
                String operand1 = stack.pop();
                String operand2 = stack.pop();
                // Combine them in postfix format and push back onto the stack
                String postfix = operand1 + operand2 + ch;
                stack.push(postfix);
            }
        }

        // The final result is the only element left in the stack
        return stack.pop();
    }
}
