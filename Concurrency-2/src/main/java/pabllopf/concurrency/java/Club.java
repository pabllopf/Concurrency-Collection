package pabllopf.concurrency.java;

// SE IMPORTAN LIBRERIAS NECESARIAS:

public class Club 
{
    private int numPelotas;
    private int numPalos;
    
    public Club(int numPelotas, int numPalos)
    {
        this.numPelotas = numPelotas;
        this.numPalos = numPalos;
    }
    
    // CONTROL DE PELOTAS DE GOLF:
    public synchronized boolean reservaPelotas(int num)
    {
        while((numPelotas - num) <= 0)
        {
            try
            {
                wait();
            }catch(InterruptedException exc){};
        }

        numPelotas -= num;
        notifyAll();
        Simulador.log(" CLUB::: Quedan: " + numPelotas + " pelotas.");
        return true;
    }
    
    public synchronized void devuelvePelotas(int num)
    {
        numPelotas += num;
        notifyAll();
        Simulador.log(" CLUB::: Quedan: " + numPelotas + " pelotas.");
    }
    
    
    // CONTROL DE PALOS DE GOLF:
    public synchronized boolean reservaPalos(int num)
    {
        while((numPalos - num) <= 0)
        {
            try
            {
                wait();
            }catch(InterruptedException exc){};
        }

        numPalos -= num;
        notifyAll();
        Simulador.log(" CLUB::: Quedan: " + numPalos + " palos.");
        return true;
    }
    
    public synchronized void devuelvePalos(int num)
    {
        numPalos += num;
        notifyAll();
        Simulador.log(" CLUB::: Quedan: " + numPalos + " numPalos.");
    }
}
