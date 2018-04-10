import java.io.File; 
import java.io.FileReader; 
import java.io.IOException; 
import java.nio.file.Files; 
import java.nio.file.Paths; 


public class LoadFile { 

private FileHandler handler; 

private LoadFile() { 
} 

public LoadFile(FileHandler handler) { 
this(); 
setHAndler(handler); 
} 


public FileHandler getHandler() { 
return handler; 
} 

public void setHAndler(FileHandler handler) { 
this.handler = handler; 
} 

public void processLoad(File file) { 

if ( file == null || !file.exists()) { 
handler.onError("No filename present"); 
return; 
} 

try { 
FileReader fileReader = new FileReader(file); 
String text = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath()))); 
Document document = new Document(file.getName(), 0, text); 
fileReader.close(); 
handler.onLoad(document); 
} catch (IOException exc) { 
handler.onError("Error opening or reading file."); 
} 
} 
} 