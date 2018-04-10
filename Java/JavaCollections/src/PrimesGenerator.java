import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;


public class PrimesGenerator implements Iterator, Iterable {
	/*protected ArrayList<Integer> Primes = new ArrayList<Integer>();
	public void setPrimes(ArrayList<Integer> a)
	{
		Primes = a;
	}
	public ArrayList<Integer> getPrimes()
	{
		return Primes;
	}
	
	public void generate(int n)
	{
		Random rand = new Random();
		ArrayList<Integer> workPrimes = new ArrayList<Integer>();
		for(int i = 0 ; i < n ; i++)
			workPrimes.add(PrimesMas[rand.nextInt(39)]);
		setPrimes(workPrimes);
	}
	static protected int[] PrimesMas = new int[40];*/
	static protected boolean isPrime(int a)
	{
		for(int i = 2 ; i <= a/2; i++)
			if(a % i == 0)return false;
		return true;
	}
	/*static
	{
		int counter = 0;
		int current = 2;
		while(counter<40)
		{
			if(isPrime(current))
			{
				PrimesMas[counter]=current;
				counter++;
			}
			current++;
		}
	}*/
	public PrimesGenerator(int n2, int currentPrime2, int currentIndex2)
	{
		n=n2; currentPrime=currentPrime2; currentIndex= currentIndex2;
	}
	public boolean goForward=true;
	public int n;
	public int currentPrime;
	public int currentIndex;
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(goForward){if(currentIndex<n-1) return true; else return false;}
		else
			return hasPrev();
	}
	@Override
	public PrimesGenerator next() {
		// TODO Auto-generated method stub
		if(goForward){int x = currentPrime+1;
		for(;;++x)
			if(isPrime(x)) break;
		currentPrime = x; currentIndex++;
		return new PrimesGenerator(n, x, currentIndex);
		}
		else
			return previous();
	
	}
	public boolean hasPrev()
	{
		if(currentIndex>0) return true; else return false;
	}
	public PrimesGenerator previous(){
		int x = currentPrime-1;
		
			for(;;--x)
				if(isPrime(x)||(x<2))
					break;
			currentPrime = x; currentIndex--;
			if(x<2)x=2;
			return new PrimesGenerator(n, x, currentIndex);
		
	}
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return this;
	}
}
