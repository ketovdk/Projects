import java.util.Date;
import java.util.Random;
public class BadGuys implements Runnable {

	ConsolePrinter printer;

	private Random rand = new Random(new Date().getTime());
	public static boolean wait = true;
	@Override
	public void run() {
		while(wait){try {
			Thread.sleep(100);
		} 
			catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	
		while(!MainClass.stop){
			if(rand.nextInt()%(MainClass.maxY-50)<(MainClass.hits.get()+MainClass.misses.get())/25+20)
				new Thread(new BadGuy(printer)).start();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub

	}
	public BadGuys(ConsolePrinter _printer){
		printer = _printer;
	}
}
