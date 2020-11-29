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
    private  int size;
    private Vector<Ewok> EwokList;


    public Ewoks(int numberOfEwoks)
    {
        EwokList=new Vector<>();
        size=numberOfEwoks;
        for (int i=1;i<=numberOfEwoks;i++)
        {
            Ewok newEwok=new Ewok(i);
            EwokList.add(newEwok);
        }
    }

    private boolean isAvailable(Ewok ewok)
    {
        return ewok.available;
    }

    private  void acquire(Ewok ewok)
    {
        int index=ewok.serialNumber-1;
        EwokList.get(index).acquire();
    }

    private void release(Ewok ewok)
    {
        int index=ewok.serialNumber-1;
        EwokList.get(index).release();

    }



}
