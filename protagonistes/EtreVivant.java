package protagonistes;

import java.io.Serializable;

public class EtreVivant implements Serializable{

    // Attributs
    protected int pvMax;
    protected int pvActuel = pvMax;
    protected int force;

    // Constructeur
    public EtreVivant(int pvMax, int force){
        this.pvMax = pvMax;
        this.pvActuel = pvMax;
        this.force = force;
    }

    // Guetteur
    public int getForce() {
        return force;
    }

    public int getPvActuel() {
        return pvActuel;
    }

    public int getPvMax() {
        return pvMax;
    }
}