import java.util.concurrent.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.atomic.*;


import javax.swing.*;

public class MainClass {

	public static void viewScore()
	{
		if(misses.get()==20){
			stop=true;
			JOptionPane.showMessageDialog(new Frame(), "Game Over"); 
		}
		else{
			gameWindow.setTitle(("Промахов: " + misses.toString() + " попаданий: " + hits.toString()));
		}
			
	}
	public static int maxX=500;
	public static int maxY=300;
	public static boolean stop=false;
	public static AtomicInteger hits=new AtomicInteger(0);
	public static AtomicInteger misses=new AtomicInteger(0);
	public static int x=maxX/2;
	public static int y=maxY/2*2-30;
	public static GameWindow gameWindow = new GameWindow(maxX, maxY);
	public static Semaphore  bulletSemaphore = new Semaphore(3, true); 
	public static ConsolePrinter printer = new ConsolePrinter();
	public static void main(String[] args) {
		
		
		BadGuys badGuys = new BadGuys(printer);
		Thread badGuysCreator = new Thread(badGuys);
		viewScore();
		
		badGuysCreator.start();
	
		
		new Thread( new Runnable(){
			public void run(){
			try {
				Thread.sleep(15000);
				BadGuys.wait=false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			}
		}).start();
		gameWindow.setVisible(true);
		printer.print(x,y,'|');
		while(!stop)
		{
			
			
			//
		}
		// TODO Auto-generated method stub

	}

}
