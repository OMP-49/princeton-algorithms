import java.util.Arrays;
import java.util.Iterator;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Board {

    private final int n;
    private final int[][] board;

    public Board(int[][] tiles) {
        n = tiles.length;
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = tiles[i][j];
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n).append(System.lineSeparator());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                sb.append(String.format("%10d", board[i][j]));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        int dist = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0 && board[i][j] != n * i + j + 1)
                    dist++;
            }
        }
        return dist;
    }

    public int manhattan() {
        int dist = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    dist += Math.abs((board[i][j] - 1) / n - i) + Math.abs((board[i][j] - 1) % n - j);
                }
            }
        }
        return dist;

    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public boolean equals(Object y) {
        if (y == this)
            return true;
        if (y == null)
            return false;
        if (y.getClass() != this.getClass())
            return false;
        Board that = (Board) y;
        if (that.dimension() != this.dimension())
            return false;
        if (Arrays.deepEquals(this.board, that.board))
            return true;
        return false;

    }

    public Iterable<Board> neighbors() {
        int[][] neighbour = new int[n][n];
        int emptyTileRow = 0;
        int emptyTileCol = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                neighbour[i][j] = board[i][j];
                if (board[i][j] == 0) {
                    emptyTileCol = j;
                    emptyTileRow = i;
                }
            }
        }

        Stack<Board> st = new Stack<>();
        if (emptyTileRow - 1 >= 0) {
            neighbour[emptyTileRow][emptyTileCol] = neighbour[emptyTileRow - 1][emptyTileCol];
            neighbour[emptyTileRow - 1][emptyTileCol] = 0;
            st.push(new Board(neighbour));
            neighbour[emptyTileRow - 1][emptyTileCol] = neighbour[emptyTileRow][emptyTileCol];
            neighbour[emptyTileRow][emptyTileCol] = 0;
        }
        if (emptyTileCol - 1 >= 0) {
            neighbour[emptyTileRow][emptyTileCol] = neighbour[emptyTileRow][emptyTileCol - 1];
            neighbour[emptyTileRow][emptyTileCol - 1] = 0;
            st.push(new Board(neighbour));
            neighbour[emptyTileRow][emptyTileCol - 1] = neighbour[emptyTileRow][emptyTileCol];
            neighbour[emptyTileRow][emptyTileCol] = 0;
        }
        if (emptyTileRow + 1 < n) {
            neighbour[emptyTileRow][emptyTileCol] = neighbour[emptyTileRow + 1][emptyTileCol];
            neighbour[emptyTileRow + 1][emptyTileCol] = 0;
            st.push(new Board(neighbour));
            neighbour[emptyTileRow + 1][emptyTileCol] = neighbour[emptyTileRow][emptyTileCol];
            neighbour[emptyTileRow][emptyTileCol] = 0;
        }
        if (emptyTileCol + 1 < n) {
            neighbour[emptyTileRow][emptyTileCol] = neighbour[emptyTileRow][emptyTileCol + 1];
            neighbour[emptyTileRow][emptyTileCol + 1] = 0;
            st.push(new Board(neighbour));
            neighbour[emptyTileRow][emptyTileCol + 1] = neighbour[emptyTileRow][emptyTileCol];
            neighbour[emptyTileRow][emptyTileCol] = 0;
        }
        return st;

    }

    public Board twin() {
        int[][] twin = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                twin[i][j] = board[i][j];
            }
        }
        if (twin[0][0] == 0) {
            int temp = twin[0][1];
            twin[0][1] = twin[1][0];
            twin[1][0] = temp;
        } else if (twin[0][1] == 0) {
            int temp = twin[0][0];
            twin[0][0] = twin[1][0];
            twin[1][0] = temp;
        } else {
            int temp = twin[0][0];
            twin[0][0] = twin[0][1];
            twin[0][1] = temp;
        }
        return new Board(twin);

    }

    public static void main(String[] agrs) {

        int[][] tiles = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tiles[i][j] = 5 * i + j;
            }
        }

        Board board = new Board(tiles);
        StdOut.println(board.toString());

        StdOut.println("Dimension of board: " + board.dimension());
        StdOut.println("Hamming distance: " + board.hamming());
        StdOut.println("Manhattan distance: " + board.manhattan());
        StdOut.println("Is this goal board: " + board.isGoal());
        Board board2 = new Board(tiles);
        StdOut.println("Is this board equal to second baord: " + board.equals(board2));

        Iterator<Board> it = board.neighbors().iterator();
        int count = 1;
        while (it.hasNext()) {
            StdOut.println("Neighbour: " + count);
            StdOut.println(it.next().toString());
            count++;
        }

        StdOut.println("Twin board");
        StdOut.println(board.twin().toString());
    }

}
