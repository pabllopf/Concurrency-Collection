package pabllopf.concurrency.java.textprocessing;
import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws InterruptedException {
        FileNames fileNames= new FileNames();
        FileContents fileContents= new FileContents(30, 100 * 1024);
        WordFrequencies wordFrequencies= new WordFrequencies();
        
        FileReader fileReader1 = new FileReader(1, fileNames, fileContents);
        Thread fileReaderHilo1 = new Thread(fileReader1);
        fileReaderHilo1.start();
        
        FileReader fileReader2 = new FileReader(2, fileNames, fileContents);
        Thread fileReaderHilo2 = new Thread(fileReader2);
        fileReaderHilo2.start();
        
        FileProcessor fileProcessor1 = new FileProcessor(1, fileContents,wordFrequencies);
        Thread fileProcessorHilo1 = new Thread(fileProcessor1);
        fileProcessorHilo1.start();
        
        FileProcessor fileProcessor2 = new FileProcessor(2, fileContents,wordFrequencies);
        Thread fileProcessorHilo2 = new Thread(fileProcessor2);
        fileProcessorHilo2.start();
        
        FileProcessor fileProcessor3 = new FileProcessor(3, fileContents,wordFrequencies);
        Thread fileProcessorHilo3 = new Thread(fileProcessor3);
        fileProcessorHilo3.start();
        
        Tools.fileLocator(fileNames, new File("src/main/java/resources").getAbsolutePath());
        fileNames.noMoreNames();
        
        fileReaderHilo1.join();
        fileReaderHilo2.join();
        
        fileProcessorHilo1.join();
        fileProcessorHilo2.join();
        fileProcessorHilo3.join();
        
        
        for( String palabra : Tools.wordSelector(wordFrequencies.getFrequencies())) {
            System.out.println(palabra);
        }
        
        System.out.println("Fin De Programa");
    }
}

class Tools {
    public static void fileLocator(FileNames fn, String dirname){
        File dir = new File(dirname);
        if ( ! dir.exists() ){
            return;
        }
        if ( dir.isDirectory() ) {
            String[] dirList = dir.list();
            for ( String name: dirList ) {
                if ( name.equals(".") || name.equals("..") ) {
                    continue;
                }
                fileLocator(fn, dir + File.separator + name);
            }
        } else {
            fn.addName(dir.getAbsolutePath());
            //System.out.println(dir.getAbsolutePath());
        }
    }
    public static class Order implements Comparator< Map.Entry<String,Integer> > {
        public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
            if ( o1.getValue().compareTo(o2.getValue()) == 0 ) {
                return o1.getKey().compareTo(o2.getKey());
            }
            return o2.getValue().compareTo(o1.getValue());
        }
    }
    
    public static List<String> wordSelector(Map<String,Integer> wf){
        Set<Map.Entry<String,Integer>> set=wf.entrySet();
        Set<Map.Entry<String,Integer>> orderSet= new TreeSet<Map.Entry<String,Integer>>(
            new Order());
        orderSet.addAll(set);
        List<String> l = new LinkedList<String>();
        int i=0;
        for ( Map.Entry<String,Integer> pair: orderSet){
            l.add(pair.getValue() + " " + pair.getKey() );
            if (++i >= 10 ) break;
        }
        return l;
    }

    public static  String getContents(String fileName){
        StringBuilder text = new StringBuilder();
		try {
		    FileInputStream fis = new FileInputStream( fileName );
	        BufferedReader br = new BufferedReader( new InputStreamReader(fis, "ISO8859-1") );
			String line;
			while (( line = br.readLine()) != null) {
				text.append(line);
				text.append("\n");
			}
		} catch (IOException e) {
			return "Error: " + e.getMessage();
		}
        return text.toString();
    }
    
}
