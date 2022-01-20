package unionFind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UnionFindMain {

	public static void main(String[] args) {
		int N = StdIn.readInt();
		MyUnionFind UF = new MyUnionFind(N);
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			
			if(!UF.connected(p, q)) {
				UF.union(p, q);
				StdOut.println(p +" " + q);	
			}
		}

	}

}
