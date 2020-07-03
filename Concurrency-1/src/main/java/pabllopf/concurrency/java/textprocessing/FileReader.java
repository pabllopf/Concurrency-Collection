package pabllopf.concurrency.java.textprocessing;
import java.io.File;

public class FileReader extends Thread{
    private int id;
    private FileNames fileNames;
    private FileContents fileContents;
    
    public FileReader(int id, FileNames fileNames, FileContents fileContents){
        this.id = id;
        this.fileNames = fileNames;
        this.fileContents = fileContents;
    }
    
    @Override
    public void run()
    {
        System.out.println("-> FileReader " + id + " empieza a leer.");
        fileContents.registerWriter();
        
        while(true){
            String fileName = fileNames.getName();
            if(fileName == null){
                System.out.println("-> FileReader " + id + " ha finalizado");
                fileContents.unregisterWriter();
                interrupt();
                return;
            }else{
                System.out.println("-> FileReader " + id + " ha leido el fichero " + new File(fileName).getName());
                fileContents.addContents(Tools.getContents(fileName));
            }
        }
    }
}

