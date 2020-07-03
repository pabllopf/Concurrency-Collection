package pabllopf.concurrency.java.textprocessing;
import java.util.LinkedList;
import java.util.Queue;
public class FileNames {
    private Queue<String> queue;
    private boolean moreNames;
    
    public FileNames(){
        queue = new LinkedList<>();
        moreNames = true;
    }
    
    public synchronized void addName(String fileName) {
        if(moreNames){
            queue.add(fileName);
        }
        notifyAll();
    }
    
    public synchronized String getName() 
    {
        String result = null;
        while(queue.isEmpty() && moreNames)
        {
            try{
                wait();
            }catch(InterruptedException exception){};
        }
        
        if(queue.size() > 0)
        {
            result = queue.poll();
        } 
        
        notifyAll();
        return result;
    }
    
    public synchronized void noMoreNames() 
    {
        moreNames = false;
        notifyAll();
    }
}