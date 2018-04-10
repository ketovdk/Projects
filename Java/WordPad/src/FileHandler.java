
public interface FileHandler { 

void onLoad(Document document); 

void onSave(Document document); 

void onError(String message); 
} 
