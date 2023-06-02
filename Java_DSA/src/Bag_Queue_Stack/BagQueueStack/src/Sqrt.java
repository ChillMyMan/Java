package Bag_Queue_Stack.BagQueueStack.src;

import edu.princeton.cs.algs4.In;

public class Sqrt implements Expressions{
    private final Expressions expression;

    public Sqrt(Expressions expression) {
        this.expression = expression;
    }

    @Override
    public int interpret() {
        return (int)Math.sqrt(expression.interpret());
    }

    public static void main(String[] args) {

    }
}
