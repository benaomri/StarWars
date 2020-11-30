package bgu.spl.mics.application.passiveObjects;


import bgu.spl.mics.MessageBusImpl;

/**
 * Passive data-object representing a Diary - in which the flow of the battle is recorded.
 * We are going to compare your recordings with the expected recordings, and make sure that your output makes sense.
 * <p>
 * Do not add to this class nothing but a single constructor, getters and setters.
 */
public class Diary {
    //Fields
    private int numberOfAttacks;
    private long HanSoloFinish;
    private long C3POFinish;
    private long R2D2Deactivate;
    private long LeiaTerminate;
    private long HanSoloTerminate;
    private long C3POTerminate;
    private long R2D2Terminate;
    private long LandoTerminate;
    private  static Diary instance=null;


    /**
     * Init numberOfAttacks to zero
     */
    private Diary() {
        numberOfAttacks = 0;
    }

    public static Diary getInstance(){
        if(instance==null)
        {
            instance=new Diary();
        }
        return instance;
    }

    /**
     * Increment number of Attacks
     */
    public void incAtt()
    {
        numberOfAttacks++;
    }

    /**
     *
     * @return LeiaTerminate
     */
    public long getLeiaTerminate() {
        return LeiaTerminate;
    }

    /**
     *
     * @param leiaTerminate
     * set LeiaTerminate
     */
    public void setLeiaTerminate(long leiaTerminate) {
        LeiaTerminate = leiaTerminate;
    }

    /**
     *
     * @return LandoTerminate
     */
    public long getLandoTerminate() {
        return LandoTerminate;
    }

    /**
     *
     * @param landoTerminate
     * set LandoTerminate
     */
    public void setLandoTerminate(long landoTerminate) {
        LandoTerminate = landoTerminate;
    }

    /**
     *
     * @return R2D2Terminate
     */
    public long getR2D2Terminate() {
        return R2D2Terminate;
    }

    /**
     *
     * @param r2D2Terminate
     * set R2D2Terminate
     */
    public void setR2D2Terminate(long r2D2Terminate) {
        R2D2Terminate = r2D2Terminate;
    }

    /**
     *
     * @return C3POTerminate
     */
    public long getC3POTerminate() {
        return C3POTerminate;
    }

    /**
     *
     * @param c3POTerminate
     * set C3POTerminate
     */
    public void setC3POTerminate(long c3POTerminate) {
        C3POTerminate = c3POTerminate;
    }

    /**
     *
     * @return HanSoloTerminate
     */
    public long getHanSoloTerminate() {
        return HanSoloTerminate;
    }


    /**
     *
     * @param hanSoloTerminate
     * set HanSoloTerminate
     */
    public void setHanSoloTerminate(long hanSoloTerminate) {
        HanSoloTerminate = hanSoloTerminate;
    }

    /**
     *
     * @return R2D2Deactivate
     */
    public long getR2D2Deactivate() {
        return R2D2Deactivate;
    }

    /**
     *
     * @param r2D2Deactivate
     * set R2D2Deactivate
     */
    public void setR2D2Deactivate(long r2D2Deactivate) {
        R2D2Deactivate = r2D2Deactivate;
    }

    /**
     *
     * @return C3POFinish
     */
    public long getC3POFinish() {
        return C3POFinish;
    }

    /**
     *
     * @param c3POFinish
     * set C3POFinish
     */
    public void setC3POFinish(long c3POFinish) {
        C3POFinish = c3POFinish;
    }

    /**
     *
     * @return HanSoloFinish
     */
    public long getHanSoloFinish() {
        return HanSoloFinish;
    }

    /**
     *
     * @param hanSoloFinish
     * set HanSoloFinish
     */
    public void setHanSoloFinish(long hanSoloFinish) {
        HanSoloFinish = hanSoloFinish;
    }
}
