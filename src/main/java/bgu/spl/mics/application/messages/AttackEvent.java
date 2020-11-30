package bgu.spl.mics.application.messages;
import bgu.spl.mics.Event;

import java.util.List;

public class AttackEvent implements Event<Boolean> {
    int duration;
    List<Integer>serials;
    public AttackEvent(int otherDuration,List<Integer>otherSerials){
        duration=otherDuration;
        serials.addAll(otherSerials);
    }
	
}
