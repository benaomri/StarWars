package bgu.spl.mics.application.passiveObjects;


import java.util.Vector;

/**
 * Passive object representing the resource manager.
 * <p>
 * This class must be implemented as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add ONLY private methods and fields to this class.
 */
public class Ewoks {
    //Fields
    private  int size;
    private Vector<Ewok> EwokList;
    private  static Ewoks instance=null;


    /**
     * Constructor
     * get  @param   numberOfEwoks
     * Initialize size and Vector<Ewok>
     */
    private Ewoks(int numberOfEwoks)
    {
        EwokList=new Vector<>();
        size=numberOfEwoks;
        for (int i=1;i<=numberOfEwoks;i++)
        {
            Ewok newEwok=new Ewok(i);
            EwokList.add(newEwok);
        }
    }


    public static Ewoks getInstance(int numberOfEwoks){
        if(instance==null)
        {
            instance=new Ewoks(numberOfEwoks);
        }
        return instance;
    }
}
