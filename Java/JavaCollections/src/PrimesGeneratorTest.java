import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.ListIterator;

public class PrimesGeneratorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		PrimesGenerator primes = new PrimesGenerator(7, 2, 1);
		while(primes.hasNext())
			System.out.printf("%d ",primes.next().currentPrime);
		System.out.println();
		primes = new PrimesGenerator(4, 13, 4);
		primes.goForward = false;
		while(primes.hasNext())
			System.out.printf("%d ", primes.next().currentPrime);
		primes = new PrimesGenerator(7,2,1);
		PrimesLi primess = new PrimesLi(primes);
		System.out.println();
		for(PrimesGenerator i: primess)
		{
			System.out.printf("%d ", i.currentPrime);
		}
	}

}
