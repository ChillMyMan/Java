package Bag_Queue_Stack.BagQueueStack.src;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayBag<Item> implements Iterable<Item>{
    private Item[] a;
    private int n;

    public ResizingArrayBag() {
        a = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] tmp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            tmp[i] = a[i];
        a = tmp;
    }

    public void add(Item item) {
        if(n == a.length)
            resize(2 * a.length);
        a[n++] = item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return a[i++];
        }
    }

    public static void main(String[] args) {
        
    }
}
