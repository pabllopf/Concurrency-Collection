package pabllopf.concurrency.java;



public class Usuario extends Thread
{
    private int id;
    private Estacion salida;
    private Estacion llegada;
    
    public Usuario(int id, Estacion salida, Estacion llegada)
    {
        this.id=id;
        this.salida = salida;
        this.llegada = llegada;
    }
    
    @Override
    public void run()
    {
        Bicicleta biciAlquilada = salida.alquila(id);
        if(biciAlquilada == null)
        {
            Log.abandona(id, (int)salida.getId());
            return;
        }else
        {
            Log.alquila(id, (int)salida.getId(),  biciAlquilada.getId());
            try
            {
                java.util.Random rnd=new java.util.Random();
                long espera= 4000 +  rnd.nextInt((7000 + 1) - 4000 );
                Log.paseando(id, (int)salida.getId(), espera);
                sleep(espera);
            }catch(InterruptedException exc){}
            
            
            Log.intentandoDevolver(id, (int)llegada.getId(), biciAlquilada.getId());
            llegada.devuelve(biciAlquilada, id);
            Log.devuelve(id, (int)llegada.getId(), biciAlquilada.getId());
            return;
        }
    }
    
}