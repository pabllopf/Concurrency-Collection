package pabllopf.concurrency.java;

public class Main {
    private static WaitingRoom room;
    private static long ini=System.currentTimeMillis();
    public static void main(String[] args) throws InterruptedException {
        room = new WaitingRoom(3);
        
        Doctor[] doctors = new Doctor[3];
        String[] doctorNames = {"House", "Jeckill", "Watson"};
        
        for (int i=0; i<doctors.length; i++) {
            doctors[i] = new Doctor(doctorNames[i], room);
        }
        
        Patient[] patients = new Patient[3];
        String[] patientNames = {"Pas1", "Pas2", "Pas3"};
        int[] patientUrgency = {4, 5, 3};
       
        
        
        for (int i=0; i< patients.length; i++) {
            patients[i] = new Patient(patientNames[i], patientUrgency[i]);
            room.receivePatient(patients[i]);
        }
        
        room.receiveUrgentPatient(new Patient("Paco", 1));
        
        for (int i=0; i<doctors.length; i++) {
            doctors[i].start();
        }
        
        room.receiveUrgentPatient(new Patient("Juana", 1));
        room.receivePatient(new Patient("Pablo", 1));
        
        for (int i=0; i<doctors.length; i++) {
            doctors[i].join();
        }
        
        log("Program ends");
        
    }
    // Visualiza la situación actual, con mensaje explicativo.
    public static void log(String message) {
            long cur=System.currentTimeMillis();
            String t="";
            t += String.format("%6.1fs      \033[1;31m%s\033[m\n", (cur-ini)/1000.0, message);
            t += showWaitingRoom(room);
            t +="------------------------------------------------------------\n";
            System.out.print(t);
    }
    public static String showWaitingRoom(WaitingRoom wr) {
        String s = String.format("\033[1;%dm",33);
        Patient[] patients = wr.getAllPatients();
        for(int i=0; i< patients.length; i++) {
            s += patients[i] + " ";
        }
        s += "\033[m\n";
        return s;
    }
}
