
public class Checker implements Runnable {

	private boolean keepWorking = true;
	private int mySum;
	public void stop()
	{
		keepWorking=false;
	}
	public Checker()
	{
		keepWorking = true;
		Account[] users=bankWindow.users;
		int sum = 0;
		for(int i = 0 ; i < bankWindow.getAmount(); ++i)
		{
			sum+=users[i].getCurrency();
		}

		mySum=sum;
	}
	public bankWindow[] windows;
	public void run()
	{
		while(keepWorking)
		{
			//try {
				//bankWindow.users.wait();
			for(int i = 0 ; i < windows.length; ++i)
			{
				windows[i].setWorking(false);
			}
			Account[] users=bankWindow.users;
				int sum = 0;
				for(int i = 0 ; i < bankWindow.getAmount(); ++i)
				{
					sum+=users[i].getCurrency();
				}
	
				System.out.println(sum);				
				for(int i = 0; i<windows.length; ++i)
					{windows[i].setWorking(true); Thread t = new Thread(windows[i]); t.start();}
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//bankWindow.users.notify();
			//} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
		}
	}

}
