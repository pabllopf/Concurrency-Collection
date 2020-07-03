package pabllopf.concurrency.java;

public class Jugador extends Thread
{
    private int id;
    private String tipoJugador;
    private Club club;
    
    private int limitePartidas = 0;
    
    public Jugador(int id, String tipoJugador, Club club)
    {
        this.id = id;
        this.tipoJugador = tipoJugador;
        this.club = club;
    }
    
    @Override
    public void run()
    {
        //EMPIEZA
        Simulador.log(" " + id + tipoJugador + " decide jugar");
        
        // Numero random de pelotas y palos que utilizara este hilo:
        int numPelotas = 0;
        int numPalos = 0;
        
        //Si es jugador experto:
        if(tipoJugador == "e")
        {
            // Solo una pelota:
            numPelotas = 1;
            
            // entre 3-5 palos:
            numPalos = 5;
        }
        
        //Si es jugador novato:
        if(tipoJugador == "n")
        {
            // entre 2-5 pelotas:
            numPelotas = 5;
            // dos palos:
            numPalos = 2;
        }

        // Empieza a funcionar la simulación:
        while(true)
        {
            boolean reservaPelota = club.reservaPalos(numPalos);
            boolean reservaPalos = club.reservaPelotas(numPelotas);
            
            // Si puede reserva un num x de pelotas y num y de palos:
            if(reservaPelota && reservaPalos)
            {
                Simulador.log(" " + id + tipoJugador +"[" + numPelotas + "," + numPalos + "] reserva");
                
                // Se pone a jugar:
                try
                {
                    int tiempoJuego = Simulador.tiempoJuego();
                    Simulador.log(" " + id + tipoJugador +"[" + numPelotas + "," + numPalos + "] juega durante " + tiempoJuego + " seg");
                    sleep(tiempoJuego);
                }catch(InterruptedException exc){};
                
                
                // Devuelve el material usado: 
                Simulador.log(" " + id + tipoJugador +"[" + numPelotas + "," + numPalos + "] devuelve material");
                club.devuelvePalos(numPalos);
                club.devuelvePelotas(numPelotas);
                    
                // Se pone a descanzar:
                try
                {
                    int tiempoDescanzo = Simulador.tiempoDescanso();
                    Simulador.log(" " + id + tipoJugador +"[" + numPelotas + "," + numPalos + "] se pone a descanzar durante " + tiempoDescanzo + " seg");
                    sleep(tiempoDescanzo);
                    limitePartidas ++;
                    
                    // Si se juega 5 partidas el jugador se cansa y se va:
                    if(limitePartidas >= 5)
                    {
                        Simulador.log(" " + id + tipoJugador + " decide irse porque ya ha jugado mucho (5 partidas)");
                        interrupt();
                        return;
                    }
                    
                }catch(InterruptedException exc){};
                
            }
        }
    }
    
    
    
  
    
}

