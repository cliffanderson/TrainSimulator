package train;

import java.util.ArrayList;

public class Train
{
	
	public Train(int location, int capacity)
	{
		passengers=new ArrayList<Passenger>();
		this.location=location;
		this.capacity = capacity;
		
		if(location==0)
		{
			this.direction=Direction.OUTBOUND;	
		}
		else
		{
			this.direction=Direction.INBOUND;	
		}
	}
	public int getCapacity()
	{
		return this.capacity;
	}
	
	public int getSize()
	{
		return this.passengers.size();
	}
	
	public int getLocation()
	{
		return location;
	}
	
	public void setLocation(int newLocal)
	{
		location=newLocal;
	}
	
	public ArrayList<Passenger> getPassengers()
	{
		return passengers;
	}
	
	public void boadTrain(Passenger newPassenger)
	{
		passengers.add(newPassenger);
	}
	
	public Direction getDirection()
	{
		return direction;
	}
	
	public void setDirection(Direction newDir)
	{
		direction=newDir;
	}
	
	private int location, capacity;
	private Direction direction;
	// max capacity
	private ArrayList<Passenger> passengers;
}