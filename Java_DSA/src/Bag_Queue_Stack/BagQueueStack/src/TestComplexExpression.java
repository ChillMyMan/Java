package Bag_Queue_Stack.BagQueueStack.src;

public class TestComplexExpression {
    public static void main(String[] args) {
        String tokenString = "7 3 + 5 * 3 - ";
        ComplexExpression suffixExpression = new ComplexExpression(tokenString);
        int res = suffixExpression.interpret();
        System.out.println("Result " + tokenString + " is " + res);
    }
}
