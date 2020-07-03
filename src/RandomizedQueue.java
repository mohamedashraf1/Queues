import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] arr;

    // construct an empty randomized queue
    public RandomizedQueue() {
        n = 0;
        arr = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (n == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (n == arr.length) {
            resize(arr.length * 2);
        }
        arr[n++] = item;
    }

    private void resize(int capacity) {
        Item[] tmp = (Item[]) new Object[capacity];
        if (n >= 0) System.arraycopy(arr, 0, tmp, 0, n);
        arr = tmp;
    }


    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        int random = StdRandom.uniform(0, n);
        Item tmp = arr[random];
        arr[random] = arr[n - 1];
        arr[n - 1] = null;
        n--;
        if (n == arr.length / 4)
            resize(arr.length / 2);

        return tmp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int random = StdRandom.uniform(0, n);
        return arr[random];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int count;

        public RandomizedQueueIterator() {
            count = 0;
        }

        public boolean hasNext() {
            return (count < n && count >= 0);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (count == n)
                throw new NoSuchElementException();
            Item tmp = arr[count];
            count++;
            return tmp;
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> obj = new RandomizedQueue<>();
        obj.enqueue(1);
        obj.enqueue(2);
        obj.enqueue(3);
        StdOut.println(obj.isEmpty());
        obj.dequeue();
        StdOut.println(obj.size());
        StdOut.println(obj.sample());
        for (Integer integer : obj) {
            StdOut.println(integer);
        }

    }
}
