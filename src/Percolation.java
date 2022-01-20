
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private boolean[][] grid;
    private int countOpenSites;
    private final WeightedQuickUnionUF uf;

    /**
     * 
     * @param n Constructor to initialize a grid n*n all sites are initially blocked
     */

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        countOpenSites = 0;
        grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
        uf = new WeightedQuickUnionUF(n * n + 2);
    }

    /**
     * 
     * @param row
     * @param col open a site (row, col) if not already open
     */

    public void open(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            countOpenSites++;
            int p = convertRowColToSite(row, col);
            if (row == 1) {
                uf.union(p, 0);
            }
            if (row == n) {
                uf.union(p, n * n + 1);
            }
            if (row - 1 > 0 && isOpen(row - 1, col)) {
                int q = convertRowColToSite(row - 1, col);
                uf.union(p, q);
            }
            if (col - 1 > 0 && isOpen(row, col - 1)) {
                int q = convertRowColToSite(row, col - 1);
                uf.union(p, q);
            }
            if (row + 1 <= n && isOpen(row + 1, col)) {
                int q = convertRowColToSite(row + 1, col);
                uf.union(p, q);
            }
            if (col + 1 <= n && isOpen(row, col + 1)) {
                int q = convertRowColToSite(row, col + 1);
                uf.union(p, q);
            }
        }

    }

    private int convertRowColToSite(int row, int col) {
        return (row - 1) * n + col;
    }

    /**
     * 
     * @param row
     * @param col
     * @return true if site is open else false
     */

    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
        return grid[row - 1][col - 1];
    }

    /**
     * 
     * @param row
     * @param col
     * @return true if site is full else false
     */
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
        int p = convertRowColToSite(row, col);
        return uf.find(p) == uf.find(0);
    }

    /**
     * Return the number of open sites in the grid
     * 
     * @return
     */
    public int numberOfOpenSites() {
        return countOpenSites;
    }

    /**
     * Return true if the grid percolates else false
     * 
     * @return
     */
    public boolean percolates() {
        return uf.find(0) == uf.find(n * n + 1);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
