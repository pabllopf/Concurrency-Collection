package pabllopf.concurrency.java.textprocessing;

import java.util.Map;
import java.util.HashMap;

public class WordFrequencies 
{
    private Map<String,Integer> currentMap;
    
    public WordFrequencies()
    {
        currentMap = new HashMap<>();
    }
    
    public synchronized void addFrequencies(Map<String,Integer> f)
    {
        for(Map.Entry<String,Integer> entry: f.entrySet())
        {
            if(currentMap.containsKey(entry.getKey()))
            {
                currentMap.put(entry.getKey(), currentMap.get(entry.getKey()) + entry.getValue());
            }else
            {
                currentMap.put(entry.getKey(),entry.getValue());
            }
        }
    }
    
    public Map<String,Integer> getFrequencies()
    {
        return currentMap;
    }
}