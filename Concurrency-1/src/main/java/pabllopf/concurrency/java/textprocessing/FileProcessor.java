package pabllopf.concurrency.java.textprocessing;
import java.util.HashMap;
import java.util.Map;

public class FileProcessor extends Thread
{
    private FileContents fc;
    private WordFrequencies wf;
    private int id;
    
    public FileProcessor(int id, FileContents fc, WordFrequencies wf)
    {
        this.fc = fc;
        this.wf = wf;
        this.id = id;
    }
    
    @Override
    public void run()
    {
        System.out.println("--> FileProcessor " + id + " empieza a procesar.");
        while(true)
        {            
            String contents = fc.getContents();
            if(contents == null)
            {
                System.out.println("--> FileProcessor " + id + " finaliza");
                interrupt();
                return;
            }else
            {
                System.out.println("--> FileProcessor " + id + " esta procesando ");
                
                Map<String, Integer> map  = new HashMap<>();
                
                String palabras[]= contents.split("\\W+");	
                for (String string : palabras) 
                {
                    if(!map.containsKey(string))
                    {
                        map.put(string, 1);
                    }else
                    {
                        map.put(string, map.get(string) + 1);
                    }
                }
                wf.addFrequencies(map);
            }
        }
    }
}

