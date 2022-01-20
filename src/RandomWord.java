import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
	public static void main(String[] args) {
		String champWord= "";
		int i=1;
		
		while(!StdIn.isEmpty()) {
			String newWord = StdIn.readString();
			boolean isChamp = StdRandom.bernoulli(1/(1.0*i));
			if(isChamp) champWord = newWord;
			i++;
		}
		StdOut.println(champWord);
	}

}
