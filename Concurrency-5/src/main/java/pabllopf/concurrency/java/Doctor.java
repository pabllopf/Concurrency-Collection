package pabllopf.concurrency.java;

public class Doctor extends Thread{
    private String name;
    private WaitingRoom room;
    
    /* Se le pasan el nombrel del doctor y la sala de espera que atiende. */
    public Doctor(String name, WaitingRoom room) 
    {
        this.name = name;
        this.room = room;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            Patient paciente = room.callPatient();
            if(paciente != null)
            {
                try
                {
                    long time = 2000*paciente.getInjureLevel();
                    Main.log("Doctor: "+ this.name + " esta atendiendo a: " + paciente.toString() + " Tiempo estimado: " + time / 1000 + "s");
                    Thread.sleep(time);
                }
                catch(InterruptedException e){}
                
                Main.log("Doctor: "+ this.name + " ha atendido a: " + paciente.getName());
            }
            else
            {
                Main.log("El doctor ("+ this.name +") ha acabado su trabajo por hoy");
                return;
            }
        }
    }
}
