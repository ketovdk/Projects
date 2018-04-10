import java.awt.Label;
import java.util.concurrent.*;
import java.util.Random;
import java.util.Date;
import java.util.concurrent.atomic.*;

public class BadGuy implements Runnable {

	ConsolePrinter printer;
	static int maxY=MainClass.maxY;
	static int maxX=MainClass.maxX;
	private Random rand = new Random(new Date().getTime());
	private int x;
	private int y;
	private char symbols[] = {'-','\\','|','/'};
	private int stringIndex=0;
	private boolean direction = true;
	private void setRandomXY()
	{
		y = rand.nextInt()%(MainClass.maxY-100);
		if(y%2==0)
			x=MainClass.maxX;
		else
			x=0;
		if(x!=0) direction=false;
	}
	
	public void run() {
		boolean firstTime=true;
		// TODO Auto-generated method stub
		while(x>=0&&x<=MainClass.maxX&&!MainClass.stop){
			boolean hitme = false;
			stringIndex=(stringIndex+1)%4;
			for(int i = 0 ; i <10; ++i){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if((MainClass.gameWindow.getComponentAt(x,y)!=null)&&!(MainClass.gameWindow.getComponentAt(x,y)).getName().equals("frame0"))//проверка поподания
				{
					if(direction) printer.remove(x-1, y, ' ');
					else
						printer.remove(x+1,y,' ');
				
					hitme=true; break;
				}
			}
			if(hitme){
				MainClass.hits.incrementAndGet();
				MainClass.viewScore();
				return;
			}
			
			if(firstTime)
				firstTime=false;
			else
				if(direction) printer.remove(x-1, y, ' ');
				else
					printer.remove(x+1,y,' ');
			
			printer.print(x,y, symbols[stringIndex]);
		//	System.out.printf("%d %d \n", x, y);
			if(direction) x++;
			else
				x--;
		}
		MainClass.misses.incrementAndGet();
		MainClass.viewScore();
	}
	public BadGuy(ConsolePrinter _printer)
	{
		printer = _printer;
		
		setRandomXY();
	}
}
