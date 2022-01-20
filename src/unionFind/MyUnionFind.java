package unionFind;

import java.util.ArrayList;

public class MyUnionFind {
	private int N;
	private int arr[];
	
	public MyUnionFind(int n) {
		super();
		N = n;
		arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i]=i;
		}
	}
	
	public void union(int p, int q) {
		for(int i=0;i<N;i++) {
			int temp = arr[q];
			if(arr[i]==temp) {
				arr[i]= arr[p];
			}
		}
	}
	
	public boolean connected(int p, int q) {
		if(arr[p]==arr[q]) return true;
		else return false;
	}
	
}
