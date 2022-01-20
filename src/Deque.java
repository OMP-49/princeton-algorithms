import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Item item) {
            this.item = item;
        }
    }

    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node current = new Node(item);
        if (head == null) {
            head = current;
            tail = current;
        } else {
            head.prev = current;
            current.next = head;
            head = current;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node current = new Node(item);
        if (tail == null) {
            head = current;
            tail = current;
        } else {
            tail.next = current;
            current.prev = tail;
            tail = current;
        }
        size++;
    }

    public Item removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        } else if (head == tail) {
            Item item = head.item;
            head = null;
            tail = null;
            size--;
            return item;
        } else {
            Item item = head.item;
            head = head.next;
            head.prev = null;
            size--;
            return item;
        }
    }

    public Item removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        } else if (head == tail) {
            Item item = tail.item;
            head = null;
            tail = null;
            size--;
            return item;
        } else {
            Item item = tail.item;
            tail = tail.prev;
            tail.next = null;
            size--;
            return item;
        }
    }

    public int size() {
        return size;
    }

    private class DequeIterator implements Iterator<Item> {

        /** returning more values **/

        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        Deque<Integer> q = new Deque<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                q.addFirst(i);
            } else {
                q.addLast(i);
            }
        }
        StdOut.println("Quiue size: " + q.size());
        StdOut.println("Is queue empty: " + q.isEmpty());
        for (int item : q) {
            StdOut.print(item + " ");
        }
        StdOut.println();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                int item = q.removeLast();
                StdOut.print(item + " ");
            } else {
                int item = q.removeFirst();
                StdOut.print(item + " ");
            }
        }
        StdOut.println();
        StdOut.println("Quiue size: " + q.size());
        StdOut.println("Is queue empty after removing all items: " + q.isEmpty());

        try {
            q.removeFirst();
        } catch (NoSuchElementException e) {
            StdOut.println("No such element exception caught");
        }
        try {
            q.removeLast();
        } catch (NoSuchElementException e) {
            StdOut.println("No such element exception caught");
        }

        try {
            q.addFirst(null);
        } catch (IllegalArgumentException e) {
            StdOut.println("No such element exception caught");
        }
        try {
            q.addLast(null);
        } catch (IllegalArgumentException e) {
            StdOut.println("No such element exception caught");
        }

        try {
            Iterator<Integer> itr = q.iterator();
            StdOut.println("Is iterator next present: " + itr.hasNext());
            itr.next();
        } catch (NoSuchElementException e) {
            StdOut.println("No such element exception caught");
        }

        try {
            Iterator<Integer> itr = q.iterator();
            itr.remove();
        } catch (UnsupportedOperationException e) {
            StdOut.println("Unsupported operation exception caught");
        }
    }

}
