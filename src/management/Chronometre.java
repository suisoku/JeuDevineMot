package management;

public class Chronometre {

    private long begin;
    private long end;
    private long current;
    private int limite;

    public Chronometre(int limite) {
        //intialisation
        begin = 0;
        end = 0;
        current = 0;
        this.limite = limite;
    }

    public void start() {
        begin = System.currentTimeMillis();
    }

    public void stop() {
        end = System.currentTimeMillis();
    }

    public long getTime() {
        return end - begin;
    }

    public long getMilliseconds() {
        return end - begin;
    }

    public int getSeconds() {
        return (int) ((end - begin) / 1000.0);
    }

    public double getMinutes() {
        return (end - begin) / 60000.0;
    }

    public double getHours() {
        return (end - begin) / 3600000.0;
    }

    /**
     * Method to know if it remains time.
     */
    public boolean remainsTime() {
        current = System.currentTimeMillis();
        int timeSpent;
        timeSpent = (int) ((begin + (limite * 1000.0) - current) / 1000.0);
        
            return (timeSpent > 0);
        
           
        

    }
    // compteRoobot sur l'ecran 

    public String toString() {
        current = System.currentTimeMillis();
        int timeSpent;
        timeSpent = (int) ((begin + (limite*1000) - current)/1000.0);
        
        return "Il vous reste : "+Integer.toString(timeSpent);
    }
    public int tempsPro(){
        return (int) ((begin + (limite * 1000.0) - current) / 1000.0);
    }
}

