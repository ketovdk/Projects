import java.util.concurrent.*;

public class Bullet implements Runnable {

	private ConsolePrinter printer;
	private int x; 
	private int y;
	
	static boolean ok = false;
	static void setOk(){
		ok=true;
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ok=false;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(!MainClass.bulletSemaphore.tryAcquire()||ok)// проверка, что пуля уже есть
			return;
		setOk();
		while(!MainClass.stop&&y>0){
			printer.print(x, y, '*');
			
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			printer.remove(x,y,' ');
			--y;
		
		}
		MainClass.bulletSemaphore.release();

	}
	public Bullet(ConsolePrinter _printer, int _x, int _y){
		x = _x;
		y = _y;
		printer = _printer;
	}
}
