
public class testingBankSystem {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 50; ++i)
			{
			String str= Integer.toString(i);
			bankWindow.add(new Account(1000, str ));
			
			}
		
		bankWindow[] windows = new bankWindow[10];
		for(int i = 0; i<10; ++i)
		{
			windows[i]=new bankWindow(i);
		}
		
		Thread[] threads = new Thread[10];
		for(int i = 0; i<10; ++i)
		{
			threads[i]=new Thread(windows[i]);
		
		}
		
		
		for(int i = 0 ; i < 10; ++i)
			threads[i].start();
		Checker check = new Checker();
		check.windows = windows;
		Thread t = new Thread(check);
		t.setPriority(9);
		t.start();
		
		
//		
		try {
			Thread.sleep(1500);
			check.stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}System.out.println("End");
	}
	
}

