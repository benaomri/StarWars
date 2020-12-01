package bgu.spl.mics;

import java.util.Vector;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;


/**
 * The {@link MessageBusImpl class is the implementation of the MessageBus interface.
 * Write your implementation here!
 * Only private fields and methods can be added to this class.
 */
public class MessageBusImpl<microServiceVector> implements MessageBus {

	private Vector microServiceVector;
	private ConcurrentHashMap<String, Vector> massageBusMS;
	private ConcurrentHashMap<Event, Vector> massageBusEV;
	private ConcurrentHashMap<Event, Future> massageBusFuture;
	private BlockingDeque bQueue;


	/**
	 * Empty constructor
	 */
	private MessageBusImpl()
	{
		massageBusMS= new ConcurrentHashMap<>();
		massageBusEV= new ConcurrentHashMap<>();
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



	@Override
	public <T> void subscribeEvent(Class<? extends Event<T>> type, MicroService m) {
		//insert to the double hashing
		massageBusMS.get(m.getName()).add(type);
		massageBusEV.get(type).add(m.getName());

	}

	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, MicroService m) {

		massageBusMS.get(m.getName()).add(type);
		massageBusEV.get(type).add(m.getName());
	}

	@Override @SuppressWarnings("unchecked")
	public <T> void complete(Event<T> e, T result) {
		massageBusFuture.get(e).resolve(result);

	}

	@Override
	public void sendBroadcast(Broadcast b) {
		//todo a loop that get all micro service that written in massgeBusEvent and add the 'b' to massgeBusMS
	}

	
	@Override
	public <T> Future sendEvent(Event<T> e) {
		Vector toRoundRobi=massageBusEV.get(e);//
		round_robin(e,toRoundRobi);
        return massageBusFuture.get(e);
	}

	@Override
	public void register(MicroService m) {
		massageBusMS.put(m.getName(),new Vector());
	}

	@Override
	public void unregister(MicroService m) {
		massageBusMS.remove(m.getName());
	}

	@Override
	public Message awaitMessage(MicroService m) throws InterruptedException {
		
		return null;
	}
	private String  round_robin(Event<T>e,Vector microSVector){
		//todo:think about round robin logic
		this.subscribeEvent();
	}
}
