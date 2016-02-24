package main;

import ADT.QueueArray;
import train.Passenger;

import java.awt.*;
import java.util.Random;

public class Station
{
	private Color color;
	public Station(int count)
	{
		Random r = new Random();
		color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		
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
	public int inQueSize()
	{
		return inboundQueue.sizeOfQueue();
	}
	public int outQueSize()
	{
		return outboundQueue.sizeOfQueue();
	}

	boolean trainPresent;
	private QueueArray<Passenger> inboundQueue, outboundQueue;
	private int id;

	public Color getColor() {
		return color;
	}
}