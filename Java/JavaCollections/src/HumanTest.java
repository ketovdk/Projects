import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.Comparator;



public class HumanTest {

	public static void main(String[] args) {
	HashSet<Human> hashSet = new HashSet<Human>();
	hashSet.add(new Human("Кетов", "Дмитрий", 20));
	hashSet.add(new Human("Кетова", "Ольгка", 40));
	hashSet.add(new Human("Аблекимов", "Эдуард", 20));
	hashSet.add(new Human("Капгер", "Галина", 21));
	for(Human h: hashSet)
		System.out.print(h.getFName()+" ");
	System.out.println("");
	LinkedHashSet<Human> linkedSet = new LinkedHashSet<Human>(hashSet);
	for(Human h: linkedSet)
		System.out.print(h.getFName()+" ");
	System.out.println("");
	TreeSet<Human> treeSet = new TreeSet<Human>(hashSet);
	for(Human h: treeSet)
		System.out.print(h.getFName()+" ");
	System.out.println("");
	treeSet = new TreeSet<Human>(new HumanComparatorByLName());
	for(Human h: hashSet)
		treeSet.add(h);
	for(Human h: treeSet)
		System.out.print(h.getFName()+" ");
	System.out.println("");
	
	treeSet = new TreeSet<Human>(AgeComparator);
	for(Human h: hashSet)
		treeSet.add(h);
	
	for (Human h: treeSet)
			System.out.print(h.getFName()+" ");
	
	
	
}
	public static Comparator<Human> AgeComparator = new Comparator<Human>()
			{
				@Override
				public int compare(Human h1, Human h2)
				{
					return h1.getAge()-h2.getAge();
				}
			};
}
