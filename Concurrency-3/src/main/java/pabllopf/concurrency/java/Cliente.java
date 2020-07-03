package pabllopf.concurrency.java;



public class Cliente extends Thread
{
    private int id;
    private Tienda tienda;
    private int gofioComprado;
    
    
    public Cliente(int id, Tienda t)
    {
        this.id=id;
        this.tienda=t;
        
        gofioComprado = 0;
    }
    
    
    @Override
    public void run()
    {
        while(true)
        {
            int cantidadAComprar =  ValoresSimulacion.cantidadAComprar();
            int stock = tienda.stock();
            boolean compra = tienda.comprar(id,cantidadAComprar);
            long tiempoConsumoKilo = ValoresSimulacion.tiempoConsumoKilo();
            
            Log.intentandoComprar(id, stock, cantidadAComprar, tiempoConsumoKilo);
            
            if(compra == true)
            {
                try
                {
                    Thread.sleep(tiempoConsumoKilo);
                }
                catch(InterruptedException exc){}
                
                Log.comprado(id, stock, cantidadAComprar);
                gofioComprado = cantidadAComprar + gofioComprado;
            }
            else
            {
                Log.noPudoComprar(id, stock,cantidadAComprar);
                return;
            } 
        }
    }    
    
    public int comprado()
    {
        return gofioComprado;
    }
}
