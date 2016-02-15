package train;

import src.TrainRoute;

public class Passenger
{
	public Passenger(TrainRoute route)
	{
		//randomly generate their destination
		this.destination = (int) (Math.random() * route.getNumStations());
	}
	
	public int getDestination()
	{
		return this.destination;
	}
	
	//Destination station ID
	private int destination;
}
