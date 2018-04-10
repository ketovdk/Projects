import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;


public class Main {

	static int  elemsNum=1000000;
	static int removesNum=10000;
	public static void main(String[] args) {
		ArrayList<Integer> array=new ArrayList<Integer>();
		LinkedList<Integer> list = new LinkedList<Integer>();

		for(int i = 0; i < elemsNum; ++i){
			array.add(i);
			list.add(i);
		}
		
		Iterator<Integer> arrayIterator = array.iterator();
		Iterator<Integer> listIterator = list.iterator();
		arrayIterator.next();
		arrayIterator.next();
		listIterator.next();
		listIterator.next();
		long arrayStartTime = System.currentTimeMillis();
		int x;
		for(int i = 0; i < removesNum; ++i)
		{
			array.remove(arrayIterator);
			//x=array.get(4);
		}
		long arrayEndTime=System.currentTimeMillis();
		long listStartTime=System.currentTimeMillis();
		for(int i = 0; i < removesNum; ++i)
		{
			list.remove(listIterator);
		}
		long listEndTime=System.currentTimeMillis();
		System.out.println("Время работы массива:");
		System.out.println(arrayEndTime-arrayStartTime);
		System.out.println("Время работы списка:");
		System.out.println(listEndTime-listStartTime);
	}

}
