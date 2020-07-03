import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;

    private class Node {
        Item value;
        Node next;
        Node before;
    }

    // construct an empty deque
    public Deque() {
        n = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (n == 0);
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (first == null) {
            first = new Node();
            last = first;
            first.value = item;
        } else {
            Node oldFirst = first;
            first = new Node();
            first.value = item;
            first.next = oldFirst;
            oldFirst.before = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (last == null) {
            first = new Node();
            last = first;
            first.value = item;
        } else {
            Node oldLast = last;
            last = new Node();
            last.value = item;
            oldLast.next = last;
            last.before = oldLast;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null)
            throw new NoSuchElementException();
        Item tmp = first.value;
        if (first.next != null) {
            first = first.next;
            first.before = null;
        } else {
            first = null;
            last = null;
        }
        n--;
        return tmp;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (first == null)
            throw new NoSuchElementException();
        Item tmp = last.value;
        if (last.before != null) {
            last = last.before;
            last.next = null;
        } else {
            first = null;
            last = null;
        }
        n--;
        return tmp;
    }


    private class QueueIterator implements Iterator<Item> {
        private Node current = first;

        public QueueIterator() {
        }

        public boolean hasNext() {
            return (current != null);
        }

        public Item next() {
            if (current == null)
                throw new NoSuchElementException();
            Item tmp = current.value;
            current = current.next;
            return tmp;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> obj = new Deque<>();
        obj.addFirst(22);
        obj.addFirst(24);
        obj.addLast(23);
        obj.addLast(25);

        obj.removeLast();
        obj.removeFirst();
        StdOut.println(obj.size());
        StdOut.println(obj.isEmpty());
        for (Integer integer : obj) {
            StdOut.println(integer);
        }

    }
}
