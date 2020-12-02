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
	private ConcurrentHashMap<String, Vector<Message>> massageBusMS;
	private ConcurrentHashMap<Event, Vector<String>> massageBusEV;
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
		Vector<String> toRoundRobin=massageBusEV.get(e);//
		round_robin(e,toRoundRobin);
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
		return  massageBusMS.get(m.getName()).remove(0);//return the first massage in queue
	}

	private String  round_robin(Event<T>e,Vector microSVector){
		//todo:think about round robin logic
		this.subscribeEvent();
	}

	private boolean checkIfRegister(MicroService m){
		return massageBusMS.containsKey(m.getName());
	}
}
