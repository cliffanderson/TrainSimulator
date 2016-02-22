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
	public int queSize()
	{
		return waitLine.sizeOfQueue();
	}

	boolean trainPresent;
	private QueueArray<Passenger> waitLine;
	private int id;

	public Color getColor() {
		return color;
	}
}