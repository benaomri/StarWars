package bgu.spl.mics.application.messages;
import bgu.spl.mics.Event;
import  bgu.spl.mics.application.passiveObjects.Diary;
import bgu.spl.mics.application.passiveObjects.Ewok;
import bgu.spl.mics.application.passiveObjects.Ewoks;

import java.util.List;
import java.util.Vector;

public class AttackEvent implements Event<Boolean> {
    long duration;
    List<Integer>serials;

    public AttackEvent()
    {

    }

    public AttackEvent(long otherDuration,List<Integer>otherSerials){
        duration=otherDuration;
        serials.addAll(otherSerials);
    }
    public void att() throws InterruptedException {

        while (Ewoks.getInstance()==null)
            wait();

         Vector<Ewok> EwokList=Ewoks.getInstance().getEwokList();

        for (int i=0;i<serials.size();i++)
        {
            int serial=serials.get(0);
            EwokList.get(serial).acquire();
        }
        Thread.sleep(duration);
        Diary.getInstance().incAtt();
        for (int i=0;i<serials.size();i++)
        {
            int serial=serials.get(0);
            EwokList.get(serial).release();
        }

    }



}
