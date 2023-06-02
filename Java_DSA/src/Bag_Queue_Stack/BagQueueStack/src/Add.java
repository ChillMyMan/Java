package Bag_Queue_Stack.BagQueueStack.src;

public class Add implements Expressions{
    private final Expressions leftExpression;
    private final Expressions rightExpression;

    public Add(Expressions leftExpression, Expressions rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret() {
        return leftExpression.interpret() + rightExpression.interpret();
    }
}
