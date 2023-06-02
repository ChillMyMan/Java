package Bag_Queue_Stack.BagQueueStack.src;

public class Number implements Expressions{
    private final int n;

    public Number(int n) {
        this.n = n;
    }

    public int interpret() {
        return n;
    }
}
