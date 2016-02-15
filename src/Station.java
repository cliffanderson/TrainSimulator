package src;
import java.util.LinkedList;
import java.util.Queue;

import train.Passenger;

public class Station
{

	public Station(int count)
	{
		waitLine= new LinkedList<Passenger>();
		id=count;
	}

	public Passenger removeFirstInLine()
	{
		//pop top member of que and load to train
		return waitLine.poll();
	}

	public boolean lineNotEmpty()
	{
		return waitLine.size() != 0;
	}

	public Queue<Passenger> getLine()
	{
		return waitLine;
	}

	public int getID()
	{
		return id;
	}

	boolean trainPresent;
	private Queue<Passenger> waitLine;
	private int id;
}