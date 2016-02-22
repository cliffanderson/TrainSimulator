package src;
import ADT.QueueArray;
import train.Passenger;

public class Station
{

	public Station(int count)
	{
		inboundQueue = new QueueArray<Passenger>();
		outboundQueue = new QueueArray<Passenger>();
		id=count;
	}

	public Passenger getFromInboundLine()
	{
		//pop top member of queue and load to train
		return this.inboundQueue.dequeue();
	}
	
	public Passenger getFromOutboundLine()
	{
		//pop top member of queue and loan to train
		return this.outboundQueue.dequeue();
	}
	
	public void enqueuePassenger(Passenger p)
	{
		//determine which queue to put passengers in
		if(p.getDestination() > this.id)
		{
			this.outboundQueue.enqueue(p);
		}
		else //id and destination will not be equal due to passenger constructor
		{
			this.inboundQueue.enqueue(p);
		}
	}

	public boolean inboundEmpty()
	{
		return this.inboundQueue.isEmpty();
	}
	
	public boolean outboundEmpty()
	{
		return this.outboundQueue.isEmpty();
	}

	public int getID()
	{
		return id;
	}

	boolean trainPresent;
	private QueueArray<Passenger> inboundQueue, outboundQueue;
	private int id;
}