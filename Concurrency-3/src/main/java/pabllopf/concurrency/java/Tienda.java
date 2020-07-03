package pabllopf.concurrency.java;


import java.util.Queue;
import java.util.LinkedList;

public class Tienda
{
    private int capacidad;
    private int stock;
    
    
    public Tienda(int capacidad)
    {
        this.capacidad = capacidad;
        stock = 0;
    }
    
    public synchronized boolean vender(int idAgricultor, int sacos) //El agricultor intenta vender N sacos de 20kg
    {
        int kilos = sacos;
        if((stock + kilos) > capacidad)
        {
            try
            {
                long tiempo = ValoresSimulacion.esperaVenta();
                wait(tiempo);
            }catch(InterruptedException exc){}
            
            notifyAll();
            return false;
        }else
        {
           stock = stock + kilos;
            //System.out.println("Se ha guardado " + kilos + "kilos");
            //System.out.println("Cantidad =" + cantidad);
            notifyAll();
            return true;
        }
    }
    
    public synchronized boolean comprar(int idCliente, int kilos)//El cliente intenta comprar N kilos
    {
        if((stock - kilos) >= 0 && kilos > 0)
        {
            stock = stock - kilos;
            //System.out.println("Cantidad =" + cantidad);
            notifyAll();
            return true;
        }else
        {
            try
            {
                long tiempo = ValoresSimulacion.esperaCompra();
                wait(tiempo);
            }catch(InterruptedException exc){}
            
            if((stock - kilos) >= 0)
            {
                stock = stock - kilos;
                notifyAll();
                return true;
            }else{
                notifyAll();
                return false;
            }
        }
    }
    
    public int stock() //kilos en stock
    {
        return stock;
    }
}
