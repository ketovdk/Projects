import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameWindow extends Frame {
	public GameWindow(int _sizeX, int _sizeY){
		super("fdgjd");
		setSize(_sizeX, _sizeY);
		setLayout(null);
		
		addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent ke){}
			public void keyPressed(KeyEvent ke){
				switch(ke.getKeyCode()){
				case KeyEvent.VK_SPACE:
					new Thread(new Bullet(MainClass.printer, MainClass.x, MainClass.y-1)).start();
					break;
				case KeyEvent.VK_LEFT:BadGuys.wait=false;
					if(MainClass.x>0) MainClass.printer.remove(MainClass.x,MainClass.y,' '); --MainClass.x; MainClass.printer.print(MainClass.x,MainClass.y,'|');
					break;
				case KeyEvent.VK_RIGHT: BadGuys.wait=false;
					if(MainClass.x<MainClass.maxX)MainClass.printer.remove(MainClass.x,MainClass.y,' '); ++MainClass.x; MainClass.printer.print(MainClass.x,MainClass.y,'|');
					break;
				}
			}
			public void keyReleased(KeyEvent ke){
				}
		});
		
	}
}
