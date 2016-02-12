package src;
public class Passenger {
	public Passenger(TrainRoute route)
	{
		//randomly generate their destination
		this.destination = (int) (Math.random() * route.getNumStations());
	}
	
	//Destination station ID
	private int destination;
}
