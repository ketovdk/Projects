import javax.swing.*; 
import java.awt.*; 

public class Environment { 

private JTextArea textArea; 
private JFrame mainFrame; 
private Service statusMessage; 

private FileHandler handler = new FileHandler() { 

@Override 
public void onSave(Document document) { 
statusMessage.setText(document.getFileName() + " saved!"); 
} 

@Override 
public void onLoad(Document document) { 
textArea.setText(document.getContent()); 
statusMessage.setText(document.getFileName() + " loaded"); 
} 

@Override 
public void onError(String message) { 
statusMessage.setText(message); 
} 
}; 

public Environment() { 

mainFrame = new JFrame("Text Editor"); 
mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
mainFrame.getContentPane().setLayout(new FlowLayout()); 
mainFrame.setSize(270, 420); 

textArea = new JTextArea(); 
JScrollPane jScrollPane = new
JScrollPane(textArea); 
jScrollPane.setPreferredSize(new Dimension(250, 200)); 
jScrollPane.getViewport().add(textArea); 

FileMenu fileMenu = new FileMenu(handler); 

mainFrame.setJMenuBar(fileMenu); 
Container container = mainFrame.getContentPane(); 
container.add(jScrollPane); 
statusMessage = new Service(); 
container.add(statusMessage); 

} 

public void run() { 
mainFrame.setVisible(true); 
} 
} 

