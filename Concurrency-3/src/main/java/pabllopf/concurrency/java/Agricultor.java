package pabllopf.concurrency.java;



public class Agricultor extends Thread
{
    private int id;
    private Tienda tienda;
    private int iteraciones;
    private int cocechaVendida;
    
    public Agricultor(int id, Tienda t)
    {
        this.id=id;
        this.tienda=t;
        iteraciones = 0;
        cocechaVendida = 0;
    }
    
    @Override
    public void run ()
    {
        while(true)
        {
            int cantidadCosechada = ValoresSimulacion.cantidadCosechada();
            int stock = tienda.stock();
            long tiempoCosecha = ValoresSimulacion.tiempoCosecha();
            
            Log.intentandoVender(id, stock, cantidadCosechada, tiempoCosecha);
            
            if(iteraciones < 5)
            {
                try
                {
                    Thread.sleep(tiempoCosecha);
                }
                catch(InterruptedException exc){}
                
                boolean venta = tienda.vender(id, cantidadCosechada);
                if(venta == true)
                {
                    Log.vendido(id, stock, cocechaVendida);
                    cocechaVendida = cantidadCosechada + cocechaVendida;
                }
                iteraciones++;
            }
            else
            {
                Log.noPudoVender(id, stock, cocechaVendida);
                return;
            }
        }
    }
    
    
    
    
    public int vendido()
    { 
        // RETORNAR nº de Kg
        return cocechaVendida; 
    }
}
