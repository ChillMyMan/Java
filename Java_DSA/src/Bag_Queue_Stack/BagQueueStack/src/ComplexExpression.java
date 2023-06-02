package Bag_Queue_Stack.BagQueueStack.src;

import java.util.Stack;

public class ComplexExpression implements Expressions {
    private String[] tokenArray;

    public ComplexExpression(String complexExpression) {
        tokenArray = complexExpression.split("\\s+");
    }

    public static boolean isOperator(String s) {
        if (s.compareTo("+") == 0 || s.compareTo("-") == 0 || s.compareTo("*") == 0 || s.compareTo("/") == 0
                || s.compareTo("sqrt") == 0)
            return true;
        else
            return false;
    }
    
    public static Expressions getOperator(String s, Expressions left, Expressions right) {
        switch (s) {
            case "+":
                return new Add(left, right);
            case "-":
                return new Substract(left, right);
            case "*":
                return new Product(left, right);
            case "/":
                return new Divide(left, right);
            case "sqrt":
                return new Sqrt(left);
        }
        return null;
    }

    public int interpret() {
        Stack<Expressions> stack = new Stack<>();
        for (String s : tokenArray) {
            if (isOperator(s)) {
                Expressions rightExpression = stack.pop();
                Expressions leftExpression = stack.pop();
                Expressions operator = getOperator(s, leftExpression, rightExpression);
                int res = operator.interpret();
                stack.push(new Number(res));
            } else {
                if (s.compareTo("") == 0)
                    continue;
                Expressions i = new Number(Integer.parseInt(s));
                stack.push(i);
            }
        }
        return stack.pop().interpret();
    }
}
