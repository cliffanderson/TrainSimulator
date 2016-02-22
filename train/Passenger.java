package train;

import src.Station;
import src.TrainRoute;

public class Passenger
{
	public Passenger(TrainRoute route, Station station)
	{
		//set equal to have while loop run until they are different
		this.destination = station.getID();
		
		while(destination == station.getID())
		{
			//randomly generate their destination
			this.destination = (int) (Math.random() * route.getNumStations());
		}
	}

	public int getDestination()
	{
		return this.destination;
	}

	//Destination station ID
	private int destination;
}
