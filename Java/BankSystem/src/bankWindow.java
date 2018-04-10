import java.util.Random;
public class bankWindow implements Runnable {

	
	static public Account[] users;
/*	static public void setUsers(Account[] _users)
	{
		users= _users;
	}
	static Account[] getUsers()
	{
		return users;
	}*/
	static private int amount;
	static public void setAmount(int _amount)
	{
		amount = _amount;
	}
	static public int getAmount()
	{
		return amount;
	}
	static
	{
		users = new Account[10000];
		amount = 0;
	}
	
	static public void add(Account input)
	{
		users[amount]=input;
		amount++;
	}
	private int numberOfWindow;
	public void setNumberOfWindow(int _numberOfWindow)
	{
		numberOfWindow=_numberOfWindow;
	}
	public int getNumberOfWindow()
	{
		return numberOfWindow;
	}
	private int iterations;
	public int getIterations()
	{
		return iterations;
	}
	public void setIterations(int _iterations){
		iterations = _iterations;
	}
	public bankWindow(int _numberOfWindow)
	{
		setNumberOfWindow(_numberOfWindow);
		setIterations(30);
	}
	private volatile boolean working = true;
	public boolean getWorking()
	{
		return working;
	}
	public void setWorking(boolean _working)
	{
		working = _working;
	}
	@Override
	public void run() {
		Random rand = new Random();
		while(working && iterations >0){
			//try {
				//users.wait();
				int from = rand.nextInt(amount-1);
		int to = rand.nextInt(amount-1);
				
				
				
					int sum = rand.nextInt(users[from].getCurrency());
					users[from].setCurrency(users[from].getCurrency()-sum);
					users[to].setCurrency(users[to].getCurrency()+sum);
					System.out.printf("Окно %d перевело %d рублей со счета %s на счет %s \n",
							numberOfWindow, sum, users[from].getName(), users[to].getName());
					//users.notify();				
	--iterations;
		}
	}

}
