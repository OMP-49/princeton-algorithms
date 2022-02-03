import java.util.Comparator;
import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private SearchNode searchNode;

    public Solver(Board initial) {

        if (initial == null) {
            throw new IllegalArgumentException();
        }
        searchNode = new SearchNode(initial, 0);
        MinPQ<SearchNode> pq = new MinPQ<>(searchNode.priorityOrder());

        SearchNode twinSearchNode = new SearchNode(initial.twin(), 0);
        MinPQ<SearchNode> twinPQ = new MinPQ<>(twinSearchNode.priorityOrder());

        while (!searchNode.board.isGoal() && !twinSearchNode.board.isGoal()) {
            Iterator<Board> it = searchNode.board.neighbors().iterator();
            while (it.hasNext()) {
                Board next = it.next();
                if (searchNode.prev == null || !searchNode.prev.board.equals(next)) {
                    SearchNode neighbour = new SearchNode(next, searchNode.moves + 1);
                    neighbour.prev = searchNode;
                    pq.insert(neighbour);
                }
            }

            searchNode = pq.delMin();

            Iterator<Board> it2 = twinSearchNode.board.neighbors().iterator();
            while (it2.hasNext()) {
                Board next = it2.next();
                if (twinSearchNode.prev == null || !twinSearchNode.prev.board.equals(next)) {
                    SearchNode neighbour = new SearchNode(next, twinSearchNode.moves + 1);
                    neighbour.prev = twinSearchNode;
                    twinPQ.insert(neighbour);
                }
            }

            twinSearchNode = twinPQ.delMin();

        }

    }

    private class SearchNode {
        Board board;
        int moves;
        SearchNode prev;
        int manhattan;
        int priority = 0;

        public SearchNode(Board board, int moves) {
            this.board = board;
            this.moves = moves;
            this.manhattan = board.manhattan();
            this.priority = this.moves + this.manhattan;
        }

        private class SearchNodeComparator implements Comparator<SearchNode> {

            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                if (o1.priority > o2.priority) {
                    return 1;
                } else if (o1.priority < o2.priority) {
                    return -1;
                } else {
                    return 0;
                }
            }

        }

        public Comparator<SearchNode> priorityOrder() {
            return new SearchNodeComparator();

        }

    }

    public boolean isSolvable() {
        if (searchNode.board.isGoal())
            return true;
        return false;

    }

    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return searchNode.moves;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        Stack<Board> st = new Stack<>();
        SearchNode sn = searchNode;
        while (sn != null) {
            st.push(sn.board);
            sn = sn.prev;
        }

        return st;

    }

    public static void main(String[] args) {

        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

    }

}
