package pabllopf.concurrency.java;


// NO TIENE EFECTO MODIFICAR ESTE FICHERO
public class Log{
    public static void creandoEstación(int estación, int numeroBicicletas){
        System.out.println("Creando estación "+estación+" con "+numeroBicicletas+" bicicletas");
    }
    public static void intentandoAlquilar(int idUsuario, int estación){
         System.out.println(1 + idUsuario + estación + "intentando alquilar");
    }
    public static void esperandoAlquilar(int idUsuario, int estación, long t){
         System.out.println(2+ idUsuario + estación + "esperando alquilar hasta "+(t/1000)+","+(t%1000)+" seg");
    }
    public static void alquila(int idUsuario, int estación, int idBicicleta){
         System.out.println(3+ idUsuario + estación + "alquila bicicleta "+idBicicleta);
    }
    public static void abandona(int idUsuario, int estación){
         System.out.println(8+ idUsuario + estación + "cansado de esperar abandona");
    }
    public static void paseando(int idUsuario, int estación, long t){
         System.out.println(4+ idUsuario + estación + "comenzando paseo de "+(t/1000)+","+(t%1000)+" seg");
    }
    public static void intentandoDevolver(int idUsuario, int estación, int idBicicleta){
         System.out.println(5+ idUsuario + estación + "intentando devolver la bicicleta "+idBicicleta);
    }
    public static void devuelve(int idUsuario, int estación, int idBicicleta){
         System.out.println(6+ idUsuario + estación + "devuelve la bicicleta "+idBicicleta);
    }
}
