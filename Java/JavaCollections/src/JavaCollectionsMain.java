import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashSet;
import java.util.Arrays;
public class JavaCollectionsMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Integer[] mas = new Integer[n];
		for(int i = 0 ; i< n ; i++)
			mas[i]=in.nextInt();
		ArrayList<Integer> lis = new ArrayList<Integer>();
		for(int i = 0 ; i < n; ++i)
			lis.add(mas[i]);
	
		
		Collections.sort(lis);
		System.out.println("Results");
		for(int i : lis)
			System.out.print(i+" ");

		System.out.println("");
		Collections.sort(lis, Collections.reverseOrder());
		for(int i : lis)
			System.out.print(i+" ");
		System.out.println("");
		Collections.shuffle(lis);
		for(int i : lis)
			System.out.print(i+" ");
		System.out.println("");
		Collections.rotate(lis, 1);
		for(int i : lis)
			System.out.printf("%d ",i);
		System.out.println("");
		HashSet<Integer> h = new HashSet<Integer>(lis);
		ArrayList<Integer> lis2  = new ArrayList<Integer>(h);
		for(int i : lis2)
			System.out.print(i+" ");
		System.out.println("");
		h = new HashSet<Integer>();
		HashSet<Integer> h2 = new HashSet<Integer>();
		for(int i : lis)
		{
			if(h.contains(i))
				h2.add(i);
			else
				h.add(i);
		}
		lis2 = new ArrayList<Integer>(h2);
		for(int i : lis2)
			System.out.print(i+" ");
		System.out.println("");
		mas =  lis.toArray(new Integer[lis.size()]);
		for(int i = 0 ; i < mas.length; ++i)
			System.out.print(mas[i]+" ");
		
	}

}
