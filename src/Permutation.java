import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int count = 0;
        RandomizedQueue<String> q = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            if (count < k) {
                q.enqueue(str);
            } else {
                int rand = StdRandom.uniform(count + 1);
                if (rand < k) {
                    q.dequeue();
                    q.enqueue(str);
                }

            }
            count++;

        }
        while (k > 0) {
            StdOut.println(q.dequeue());
            k--;
        }

    }

}