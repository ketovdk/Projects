import java.util.Comparator;


public class HumanComparatorByLName implements Comparator<Human> {

	@Override
	public int compare(Human arg0, Human arg1) {
		// TODO Auto-generated method stub
		return arg0.getLName().compareToIgnoreCase(arg1.getLName());
	}
	

}
