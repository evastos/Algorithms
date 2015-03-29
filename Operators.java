package com.facebook.eva.algorithm;

import java.util.Stack;

/**
 * Created by Eva on 22.2.2015..
 */
public class Operators {

    /**
     * Calculate an expression with operators '+','-','*','/'.
     */

    private static final char ADD = '+';
    private static final char SUBTRACT = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';

    private static final String EXPRESSION = "27-13*1-12+400/200";

    public static String testCalculateExpression() {
        StringBuilder builder = new StringBuilder();
        builder.append("Expression ").append(EXPRESSION).append(" = ").append
                (calculateExpression(EXPRESSION));
        return builder.toString();
    }

    public static int calculateExpression(String expression) {
        if (expression == null) {
            return 0;
        }

        Stack<Integer> operands = new Stack<Integer>();
        Stack<Character> operators = new Stack<Character>();

        int operand = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                operand = operand * 10 + (c - '0');
            } else {
                char operator = c;
                operators.push(operator);
                operands.push(operand);
                operand = 0;
            }
        }
        operands.push(operand);

        int result = 0;

        while (!operators.isEmpty()) {
            char operator = operators.pop();
            if (operator == ADD || operator == SUBTRACT) {
                operand = operands.pop();
                result = execute(result, operand, operator);
            } else if (operator == MULTIPLY || operator == DIVIDE) {
                int operand2 = operands.pop();
                int operand1 = operands.pop();
                int newOperand = execute(operand1, operand2, operator);
                operands.push(newOperand);
            }
        }

        while (!operands.isEmpty()) {
            result += operands.pop();
        }

        return result;
    }

    private static int execute(int a, int b, char operator) {
        switch (operator) {
            case ADD:
                return a + b;
            case SUBTRACT:
                return a - b;
            case MULTIPLY:
                return a * b;
            case DIVIDE:
                return a / b;
            default:
                return a;
        }
    }
}
