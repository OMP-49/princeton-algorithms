import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int size;
    private int noOfItems;
    private int peek;

    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
        size = 1;
        noOfItems = 0;
        peek = -1;
    }

    public boolean isEmpty() {
        return noOfItems == 0;
    }

    public int size() {
        return noOfItems;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (noOfItems < size) {
            arr[noOfItems] = item;
            noOfItems++;
        } else {
            resize(size * 2);
            size = size * 2;
            arr[noOfItems] = item;
            noOfItems++;
        }
        peek = -1;
    }

    public Item dequeue() {
        if (noOfItems == 0)
            throw new NoSuchElementException();
        if (peek == -1)
            peek = StdRandom.uniform(noOfItems);
        Item result = arr[peek];
        if (peek == noOfItems - 1) {
            arr[peek] = null;

        } else {
            arr[peek] = arr[noOfItems - 1];
            arr[noOfItems - 1] = null;
        }
        peek = -1;
        if (noOfItems-- == size / 4) {
            resize(size / 2);
            size = size / 2;
        }
        return result;
    }

    public Item sample() {
        if (noOfItems == 0)
            throw new NoSuchElementException();
        peek = StdRandom.uniform(noOfItems);
        return arr[peek];
    }

    private void resize(int arrSize) {
        Item[] newArr = (Item[]) new Object[arrSize];
        for (int i = 0; i < noOfItems; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;

    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {

        private final int size = noOfItems;
        private Item[] itrArr = (Item[]) new Object[noOfItems];
        private int current = 0;

        public RandomizedIterator() {
            for (int i = 0; i < noOfItems; i++) {
                itrArr[i] = arr[i];
            }
            StdRandom.shuffle(itrArr);
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public Item next() {
            if (current >= size)
                throw new NoSuchElementException();
            Item item = itrArr[current];
            current++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        for (int i = 0; i < 5; i++) {
            q.enqueue(i);
        }
        for (int i : q) {
            for (int j : q) {
                StdOut.print(i + "-" + j + " ");
            }
            StdOut.println();
        }

        for (int i = 0; i < 5; i++) {
            StdOut.println("Is queue empty: " + q.isEmpty());
            StdOut.println("Size of queue: " + q.size());
            StdOut.println("Sample item before dequing: " + q.sample());
            StdOut.println("Removing sampled item: " + q.dequeue());
        }
        StdOut.println("Size of queue: " + q.size());
        StdOut.println("Is queue empty: " + q.isEmpty());

        try {
            q.dequeue();
        } catch (NoSuchElementException e) {
            StdOut.println("No such element exception caught");
        }

        try {
            q.enqueue(null);
            StdOut.println("Exception is not caught");
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
