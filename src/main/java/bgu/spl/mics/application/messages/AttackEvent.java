package bgu.spl.mics.application.messages;
import bgu.spl.mics.Event;

import java.util.List;

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
        Thread.sleep(duration);

    }



}
