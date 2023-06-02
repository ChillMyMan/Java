import Bag_Queue_Stack.BagQueueStack.src.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> q = new Queue<Integer>();
        for(int i = 0; i < 10; i++)
            q.enqueue(i);
        for(Integer i : q)
            System.out.println(i);
    }
}
