package pabllopf.concurrency.java;
public class Patient {
    private String name;
    private int injureLevel;

    public Patient(String name, int injureLevel) {
        this.name = name;
        this.injureLevel = injureLevel;
    }
    
    /* Devuelve el nombre del paciente. */
    public String getName(){
        return name;
    }
    
    /* Devuelve el nivel de urgencia del paciente. */
    public int getInjureLevel() {
        return injureLevel;
    }
    
    // Pasa a string el nombre del paciente
    public String toString() {
        return "name:" + getName() + " level: " + getInjureLevel();
    }
}
