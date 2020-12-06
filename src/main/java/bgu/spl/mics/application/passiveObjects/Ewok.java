package bgu.spl.mics.application.passiveObjects;

/**
 * Passive data-object representing a forest creature summoned when HanSolo and C3PO receive AttackEvents.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You may add fields and methods to this class as you see fit (including public methods).
 */
public class Ewok {
    int serialNumber;
    boolean available;

    public Ewok(int serialNumber)
    {
        this.serialNumber=serialNumber;
        this.available=true;
    }
    /**
     * Acquires an Ewok
     * @PRE: available is true
     * @POST:  available is false
     */
    public void acquire(){

        if(!available)
            throw  new UnsupportedOperationException("Ewok is not available ");
        this.available=false;
    }

    /**
     * release an Ewok
     *  @PRE: available is false
     *  @POST:  available is true
     */
    public void release() {
        if(available)
            throw  new UnsupportedOperationException("Ewok is available ");
        this.available=true;

    }
}
