
public class Human implements Comparable<Human> {

	protected String fName;
	public void setFName(String input)
	{
		fName=input;
	}
	public String getFName()
	{
		return fName;
	}
	protected String lName;
	public void setLName(String input)
	{
		lName=input;
	}
	public String getLName()
	{
		return lName;
	}
	int age;
	public void setAge(int input)
	{
		age=input;
	}
	public int getAge()
	{
		return age;
	}
	@Override
	public int compareTo(Human o){
	int rez;
	if(this.equals(o)) return 0;
	rez=fName.compareToIgnoreCase(o.getFName());
	if(rez!=0) return rez;
	lName.compareToIgnoreCase(o.getLName());
	if(rez!=0) return rez;
	return age-o.getAge();
	}
	public Human(String inputFName,String inputLName, int inputAge )
	{
		setFName(inputFName);
		setLName(inputLName);
		setAge(inputAge);
	}
	
}
