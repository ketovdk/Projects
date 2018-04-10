import java.awt.Component;
import java.awt.Label;
import java.util.concurrent.locks.ReentrantLock;

public class ConsolePrinter {
	static public ReentrantLock re = new ReentrantLock(); 
	public synchronized void print(int x, int y, char c){
		Label label = new Label(String.valueOf(c));
		label.setBounds(x,y,10,15);	
		if(c!=' '){re.lock();
		MainClass.gameWindow.add(label);
		re.unlock();}
	}
	public  void remove(int x, int y, char c)
	{
		
		Component component
	;	component = MainClass.gameWindow.getComponentAt(x,y);
	re.lock();
		if(component!=null)
		{MainClass.gameWindow.remove(component); 

		}	re.unlock();
	}
	
}
