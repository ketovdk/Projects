import javax.swing.*; 
import java.awt.*; 

public class Service extends JLabel { 

private static final long serialVersionUID = 1L; 

public Service() { 
super(); 
setPreferredSize(new Dimension(200, 30)); 
setHorizontalAlignment(SwingConstants.CENTER); 
} 

public Service(String message) { 
this(); 
setMessage(message); 
} 

public void setMessage(String message) { 
setText(message); 
} 
} 