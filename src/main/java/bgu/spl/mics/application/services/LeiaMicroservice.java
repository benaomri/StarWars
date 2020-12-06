package bgu.spl.mics.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import bgu.spl.mics.CallbackImpl;
import bgu.spl.mics.Message;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.AttackEvent;
import bgu.spl.mics.application.messages.TerminateBroadCast;
import bgu.spl.mics.application.passiveObjects.Attack;

/**
 * LeiaMicroservices Initialized with Attack objects, and sends them as  {@link AttackEvents}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link AttackEvents}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class LeiaMicroservice extends MicroService {
	private Attack[] attacks;
    private Map<Message, CallbackImpl> callbackMap;


    public LeiaMicroservice(Attack[] attacks) {
        super("Leia");
		this.attacks = attacks;
    }

    @Override
    protected void initialize() {
        subscribeBroadcast(TerminateBroadCast.class, c -> terminate());
        sendAttEvent();



    }
    private void sendAttEvent(){
        for(Attack att:attacks){
            sendEvent(new AttackEvent(att.getDuration(),att.getSerials()));
        }


    }
}
