package pabllopf.concurrency.java;

public class Simulador 
{
    private static long tini=System.currentTimeMillis();
    private static java.util.Random rand= new java.util.Random();
    
    public static void main(String[] args) 
    {
        log("Empieza la Simulación:");
        // SE CREA UN CLUB PARA EL CORRECTO FUNCIONAMIENTO:
        Club club = new Club(20,20);
        
        
        // SE CREAN 14 HILOS DE JUGADORES EXPERTOS Y NOVATOS:
        Thread jugador1e = new Jugador(1,"e",club);
        Thread jugador2e = new Jugador(2,"e",club);
        Thread jugador3e = new Jugador(3,"e",club);
        Thread jugador4e = new Jugador(4,"e",club);
        Thread jugador5e = new Jugador(5,"e",club);
        Thread jugador6e = new Jugador(6,"e",club);
        Thread jugador7e = new Jugador(7,"e",club);
        
        Thread jugador8n = new Jugador(8,"n",club);
        Thread jugador9n = new Jugador(9,"n",club);
        Thread jugador10n = new Jugador(10,"n",club);
        Thread jugador11n = new Jugador(11,"n",club);
        Thread jugador12n = new Jugador(12,"n",club);
        Thread jugador13n = new Jugador(13,"n",club);
        Thread jugador14n = new Jugador(14,"n",club);
        
        // SE LANZAN TODOS LOS HILOS:
        jugador1e.start();
        jugador2e.start();
        jugador3e.start();
        jugador4e.start();
        jugador5e.start();
        jugador6e.start();
        jugador7e.start();
        
        jugador8n.start();
        jugador9n.start();
        jugador10n.start();
        jugador11n.start();
        jugador12n.start();
        jugador13n.start();
        jugador14n.start();
        
        
        //SE ESPERA QUE TODOS LOS HILOS ACABEN:
        try
        {
            jugador1e.join();
            jugador2e.join();
            jugador3e.join();
            jugador4e.join();
            jugador5e.join();
            jugador6e.join();
            jugador7e.join();
            
            jugador8n.join();
            jugador9n.join();
            jugador10n.join();
            jugador11n.join();
            jugador12n.join();
            jugador13n.join();
            jugador14n.join();
            
        }catch(InterruptedException exc){};
        
        
        // Complete aquí la simulación
        log("Termina la Simulación.");
    }
    
    // Muestra información en la pantalla
    public static void log(String message) {
            long cur=System.currentTimeMillis();
            String t= String.format("%6.1fs      \033[1;33m%s\033[m\n", (cur-tini)/1000.0, message);
            System.out.print(t);
    }
    
    // Devuelve tiempo de juego en milisegundos
    public static int tiempoJuego() {
        return rand.nextInt(1000);
    }
    
    // Devuelve tiempo de descanso en milisegundos
    public static int tiempoDescanso() {
        return rand.nextInt(1000);
    }
}