public class Document { 

private String fileName; 
private String content; 
private int caretPosition; 

public Document(String fileName, int caretPosition, String content) { 
this.setFileName(fileName); 
this.setCaretPosition(caretPosition); 
this.setContent(content); 
} 

public String getFileName() { 
return fileName; 
} 

public void setFileName(String fileName) { 
this.fileName = fileName; 
} 

public String getContent() { 
return content; 
} 

public void setContent(String content) { 
this.content = content; 
} 

public int getCaretPosition() { 
return caretPosition; 
} 

public void setCaretPosition(int caretPosition) { 
this.caretPosition = caretPosition; 
} 
}
