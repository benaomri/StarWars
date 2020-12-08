package bgu.spl.mics;

import bgu.spl.mics.application.passiveObjects.Diary;

import java.util.Vector;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

import static java.lang.Thread.sleep;


/**
 * The {@link MessageBusImpl class is the implementation of the MessageBus interface.
 * Write your implementation here!
 * Only private fields and methods can be added to this class.
 */
public class MessageBusImpl<microServiceVector> implements MessageBus {
	private  static MessageBusImpl instance=null;//singleton expression

	/**
	 * We Use 3 Maps:
	 * 1. Key: MS String, val: Messeges vector
	 * 2. Key: Event , val: MS string that subscribe it
	 * 3. Key: Event , val: Future
	 */
	private Vector microServiceVector;
	private ConcurrentHashMap<String, Vector<Message>> massageBusMS;
	private ConcurrentHashMap<Event, Vector<String>> massageBusEV;
	private ConcurrentHashMap<Event, Future> massageBusFuture;
	private BlockingDeque bQueue;


	/**
	 * Empty constructor
	 */
	private MessageBusImpl()
	{
		massageBusMS= new ConcurrentHashMap<>(); //We had to this when MS is register
		massageBusEV= new ConcurrentHashMap<>(); //We had to this when MS is subscribe it
		massageBusFuture= new ConcurrentHashMap<>();
		bQueue=new LinkedBlockingDeque();

	}

	/**
	 * Creating singlteon MessageBusImpl or Return the same instance
	 * @return Singelton instance of MessageBusImpl
	 */
	public static MessageBusImpl getInstance(){
		if(instance==null)
		{
			instance=new MessageBusImpl();
		}
		return instance;
	}

	/**
	 * We will add the MicroService to the vector of the Event
	 * @param type The type to subscribe to,
	 * @param m    The subscribing micro-service.
	 * @param <T>
	 */
	@Override
	public <T> void subscribeEvent(Class<? extends Event<T>> type, MicroService m) {
		massageBusEV.get(type).add(m.getName());

	}


	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, MicroService m) {
		massageBusEV.get(type).add(m.getName());
	}



	@Override @SuppressWarnings("unchecked")
	public <T> void complete(Event<T> e, T result) {
		massageBusFuture.get(e).resolve(result);

	}

	@Override
	public void sendBroadcast(Broadcast b) {

		Vector<String>broadcastMicroS=massageBusEV.get(b);//get all microService that subscribe to the broadCast
		for(String s:broadcastMicroS){//insert to each microservice the broadcast
			massageBusMS.get(s).add(b);
		}
		notifyAll();//notify to all thered that is their new massage
	}

	/**
	 *
	 * @param e     	The event to add to the queue.
	 * @param <T>
	 * @return
	 */
	@Override
	public synchronized <T> Future sendEvent(Event<T> e) {
		Vector<String> toRoundRobin=massageBusEV.get(e);//
		String chosenMicro= round_robin(e,toRoundRobin);//send to round robin all microservice that subscribe to this event
		massageBusMS.get(chosenMicro).add(e);
		notifyAll();
        return massageBusFuture.get(e);
	}

	@Override
	public void register(MicroService m) {
		massageBusMS.put(m.getName(),new Vector<Message>());
	}

	@Override
	public void unregister(MicroService m) {
		massageBusMS.remove(m.getName());
	}

	@Override
	public synchronized Message awaitMessage(MicroService m) throws InterruptedException {
		if(!checkIfRegister(m))
			throw new IllegalStateException(m.getName()+"is not register");

		while(massageBusMS.get(m.getName()).isEmpty()){//wait until is massage to take
			wait();
		}
//		return null;
		return  massageBusMS.get(m.getName()).get(0);
	}

	private String  round_robin(Event e,Vector microSVector){
		String microName=massageBusEV.get(e).firstElement();
		massageBusEV.get(e).remove(0);
		massageBusEV.get(e).add(microName);//add to the end of the quque
		return microName;
	}

	private boolean checkIfRegister(MicroService m){
		return massageBusMS.containsKey(m.getName());
	}
}
