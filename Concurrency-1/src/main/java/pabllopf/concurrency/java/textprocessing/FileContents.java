package pabllopf.concurrency.java.textprocessing;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Queue;
public class FileContents {
    private int maxFiles;
    private int maxChars;
    private int currentChars;

    private Queue<String> queue;
    
    private int registerCount;
    private boolean closed;
    
    public FileContents(int maxFiles, int maxChars) {
        this.maxFiles = maxFiles;
        this.maxChars = maxChars;
        this.currentChars = 0;
        
        this.queue = new LinkedList<>();
        
        this.registerCount = 0;
        this.closed = false;
    }
    
    public synchronized void registerWriter() {
        registerCount++;
        notifyAll();
        System.out.println("===> registerCount:" + registerCount);
    }
    
    public synchronized void unregisterWriter() {
        registerCount --;
        
        if(registerCount == 0){
            closed = true;
        }
        
        notifyAll();
        System.out.println("===> registerCount:" + registerCount);
    }
    
    public synchronized void addContents(String contents) {
        while(queue.size() + 1 >= maxFiles && currentChars >= maxChars){
            try{
                System.out.println("-----> Esperando para añadir contenido");
                wait();
           }catch(InterruptedException exception){};
        }
        
        currentChars += contents.length();
        queue.add(contents);
        notifyAll();
    }
    
    public synchronized String getContents() {
        while(queue.isEmpty() && closed == false){
            try{
               wait();
           }catch(InterruptedException exception){};
        }
        
        String result = queue.poll();
        
        if(result == null){
            notifyAll();
            return null;
        }else{
            currentChars -= result.length();
            notifyAll();
            return result;
        }
    }
}

