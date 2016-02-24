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
		lastLocation=location;
		location=newLocal;
	}
	
	public ArrayList<Passenger> getPassengers()
	{
		return passengers;
	}
	
	public void boardTrain(Passenger newPassenger)
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

	public int getLastLocation()
	{
		return lastLocation;
	}
	public long getDepartureTime()
	{
		return departureTime;
	}
	public void stopped()
	{
		moving=false;
	}
	public void move()
	{
		departureTime=System.currentTimeMillis();
		moving=true;
	}
	public boolean isMoving()
	{
		return moving;
	}
	public void changePos(double pos)
	{

		if(direction==Direction.OUTBOUND)
		{
			this.pos=this.pos+pos;
		}
		else
		{
			this.pos=this.pos-pos;
		}
	}
	public double getPos()
	{
		return pos;
	}

	private boolean moving=false;
	private long departureTime=0;
	private double pos;
	private int location, capacity,lastLocation;
	private Direction direction;
	// max capacity
	private ArrayList<Passenger> passengers;
}