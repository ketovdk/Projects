import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.io.File; 

import javax.swing.JFileChooser; 
import javax.swing.JMenu; 
import javax.swing.JMenuBar; 
import javax.swing.JMenuItem; 
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;



public class FileMenu extends JMenuBar { 

private static final long serialVersionUID = 1L; 

private FileMenu() { 
super(); 
} 

public FileMenu(FileHandler handler) { 
this(); 
JMenu menu = new JMenu("File"); 
JMenuItem newItem = new JMenuItem("New");
JMenuItem loadItem = new JMenuItem("Load"); 
JMenuItem saveItem = new JMenuItem("Save");
JMenuItem saveAsItem = new JMenuItem("SaveAs");

JFileChooser fileChooser = new JFileChooser(); 
LoadFile fileManager = new LoadFile(handler); 

//fileChooser.setFileFilter(new FileNameExtensionFilter("txt"));
menu.add(newItem);
menu.add(loadItem); 
menu.add(saveItem);
menu.add(saveAsItem);
add(menu); 
loadItem.addActionListener(new ActionListener() { 

@Override 
public void actionPerformed(ActionEvent e) { 

	fileChooser.setDialogTitle("Загрузка файла");
int result = fileChooser.showOpenDialog(null); 

if (result == JFileChooser.APPROVE_OPTION) { 
File file = fileChooser.getSelectedFile(); 
fileManager.processLoad(file); 
} else { 
fileManager.getHandler().onError("No file selected."); 
} 
} 
}); 

newItem.addActionListener(new ActionListener() { 

@Override 
public void actionPerformed(ActionEvent e) { 
	 
	fileChooser.setDialogTitle("Загрузка файла");
int result = fileChooser.showOpenDialog(null);
if (result == JFileChooser.APPROVE_OPTION) { 
File file = fileChooser.getSelectedFile(); 
fileManager.processLoad(file); 
} else { 
fileManager.getHandler().onError("No file selected."); 
} 
} 
}); 

saveItem.addActionListener(new ActionListener() { 

@Override 
public void actionPerformed(ActionEvent e) { 
fileChooser.setDialogTitle("Сохранение файла");
fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
int result = fileChooser.showSaveDialog(FileMenu.this);
if(result==JFileChooser.APPROVE_OPTION)
	JOptionPane.showMessageDialog(FileMenu.this, "Файл сохранен");
} 
}); 

saveAsItem.addActionListener(new ActionListener() { 

@Override 
public void actionPerformed(ActionEvent e) { 

int result = fileChooser.showOpenDialog(null); 

if (result == JFileChooser.APPROVE_OPTION) { 
File file = fileChooser.getSelectedFile(); 
fileManager.processLoad(file); 
} else { 
fileManager.getHandler().onError("No file selected."); 
} 
} 
}); 



} 
} 
