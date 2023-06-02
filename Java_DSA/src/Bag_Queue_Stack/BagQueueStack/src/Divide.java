package Bag_Queue_Stack.BagQueueStack.src;

import java.util.NoSuchElementException;

public class Divide implements Expressions{
    private final Expressions leftExpression;
    private final Expressions rightExpression;

    public Divide(Expressions leftExpression, Expressions rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret() {
        if(rightExpression.interpret() == 0)
            throw new NoSuchElementException("Right expression must be different from 0");
        return leftExpression.interpret() / rightExpression.interpret();
    }
}
