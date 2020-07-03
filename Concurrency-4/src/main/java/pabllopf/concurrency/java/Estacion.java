package pabllopf.concurrency.java;


import java.util.ArrayList;

public class Estacion extends Thread
{
    private int id;
    private int capacidadBicis;
    
    ArrayList <Bicicleta> bicis;
    
    
    public Estacion(int id, int capacidadBicis)
    {
        this.id = id;
        this.capacidadBicis = capacidadBicis;
        bicis = new ArrayList();
        
        for(int i =0; i < capacidadBicis;i++)
        {
            int idBici = id*1000 + i;
            bicis.add(new Bicicleta(idBici));
        }
        
        Log.creandoEstación(id, capacidadBicis);
    }
    
    
    
    public synchronized Bicicleta alquila(int idUsuario)
    {
        while(capacidadBicis <= 0)
        {
            Log.intentandoAlquilar(idUsuario, id);
            try
            {
                wait(10000);
            }catch(InterruptedException exc){}
            

            return null;
        }
        
        for(int i = 0;i < bicis.size();i++)
        {
            Bicicleta biciAlquilada = bicis.get(i);
            bicis.remove(i);
            return biciAlquilada;
        }
        
        return null;
    }
    
    public synchronized void devuelve(Bicicleta bici, int idUsuario)
    {
        while(bicis.size() == capacidadBicis)
        {
            try
            {
                wait();
            }catch(InterruptedException exc){}
        }
        
         bicis.add(bici);
        return;
    }
    
    
    
    public long getId()
    {
        return id;
    }
    
    
    
}