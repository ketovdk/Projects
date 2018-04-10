
public class Account {

	private int currency = 100000;
	public  int getCurrency()
	{
		return currency;
	}
	public  synchronized void setCurrency(int a)
	{
		currency = a;
	}
	private String name = "";
	public String getName()
	{
		return name;
	}
	public void setName(String nName)
	{
		name = nName;
	}
	public Account(int _currency, String _name)
	{
		currency = _currency;
		setName(_name);
	}
	
}
