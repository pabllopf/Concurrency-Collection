package pabllopf.concurrency.java;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingRoom {
    private LinkedList<Patient> patients;
    private int sizeOfRoom;
    private boolean closed;
    
    /* Capacidad normal de la sala de espera */
    public WaitingRoom(int size)
    {
        this.sizeOfRoom = size;
        patients = new LinkedList<>();
        closed = false;
    }

    /* Cerrar la sala de espera a pacientes NO-urgentes.
       Lo usa la clase PatientGenerator. */
    public synchronized void close() 
    {
        closed = true;
        notifyAll();
    }

    /* Llega un paciente NO-urgente, limitado por la capacidad normal.
       Lo usa la clase PatientGenerator. */
    public synchronized void receivePatient(Patient patient) 
    {   
        while(patients.size() == sizeOfRoom){
           try
            {
                Main.log("La sala de espera está llena. El paciente " + patient.getName() + " está esperando.");
                wait(5000);
            }
            catch(InterruptedException e){} 
        }
        
        if(patients.isEmpty()){
            patients.offerFirst(patient);
        }else{
            if(patient.getInjureLevel() >= patients.getFirst().getInjureLevel()){
                patients.offerFirst(patient);
            }else{
                patients.offerLast(patient);
            }
        }
        
        Main.log("Paciente " + patient.getName() + " ha entrado a la sala.");
        notifyAll();
    }
   
    /* Llega un paciente urgente, no afectado por la capacidad normal.
       Lo usa la clase PatientGenerator. */
    public synchronized void receiveUrgentPatient(Patient patient)
    {
       patients.offerFirst(patient);
       Main.log("Paciente Urgente " + patient.getName() + " ha entrado a la sala.");
       notifyAll();
    }

    /* Devuelve el primer paciente de la cola, para ser atendido. */
    public synchronized Patient callPatient() 
    {        
        Patient result = patients.pollFirst();
        notifyAll();
        return result;
    }

    /* Devuelve un array con los pacientes en cola de espera, en su orden. */
    public synchronized Patient[] getAllPatients()
    {
        return new Patient[0];
    }
}
