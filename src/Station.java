package src;
import ADT.QueueArray;
import train.Passenger;

public class Station
{

	public Station(int count)
	{
		waitLine= new QueueArray<Passenger>();
		id=count;
	}

	public Passenger removeFirstInLine()
	{
		//pop top member of que and load to train
		return waitLine.dequeue();
	}
	
	public void enqueuePassenger(Passenger p)
	{
		this.waitLine.enqueue(p);
	}

	public boolean lineNotEmpty()
	{
		return !waitLine.isEmpty();
	}

	public int getID()
	{
		return id;
	}

	boolean trainPresent;
	private QueueArray<Passenger> waitLine;
	private int id;
}