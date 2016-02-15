package src;

import java.util.ArrayList;
import java.util.List;

import train.Direction;
import train.Passenger;
import train.Train;

public class Main
{
	//specify size of route
	static final int ROUTE_LENGTH = 5;
	static TrainRoute route;

	public static void main(String[] args) throws Exception
	{
		System.out.println("Train Simulator 5000");

		//create two trains, passing the start location and capacity
		Train trainA = new Train(0, 100);
		Train trainB = new Train(ROUTE_LENGTH-1, 100);

		//Array to hold the trains
		Train[] trains = {trainA, trainB};


		//create route
		route = new TrainRoute(ROUTE_LENGTH);		

		createPassengerGenerationThread();

		//primary loop
		while (true)
		{
			//loop through all trains
			for(int i = 0; i < 2; i++)
			{
				//get a train from array
				Train trainGeneric = trains[i];

				//remove passengers who are at their stop
				removePassengersFromTrain(trainGeneric);

				//load people onto the train
				loadPassengersOnToTrain(trainGeneric);

				//move trains along their tracks
				moveTrain(trainGeneric);
			}

			try
			{
				Thread.sleep(500);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private static void moveTrain(Train trainGeneric)
	{
		// if headed right
		if (trainGeneric.getDirection().equals(Direction.RIGHT))
		{
			if (trainGeneric.getLocation()==(ROUTE_LENGTH-1))
			{
				trainGeneric.setDirection(Direction.LEFT);
				trainGeneric.setLocation(trainGeneric.getLocation()-1);
			}
			else
			{
				trainGeneric.setLocation(trainGeneric.getLocation()+1);
			}
		}
		else //headed left
		{
			//if at end of track
			if (trainGeneric.getLocation()==(0))
			{
				trainGeneric.setDirection(Direction.RIGHT);
				trainGeneric.setLocation(trainGeneric.getLocation()+1);
			}
			else
			{
				trainGeneric.setLocation(trainGeneric.getLocation()-1);
			}
		}
	}

	private static void loadPassengersOnToTrain(Train train)
	{
		while(train.getSize() < train.getCapacity())
		{
			//while train has space board people onto train
			if(route.getStation(train.getLocation()).lineNotEmpty())
			{
				train.boadTrain(route.getStation(train.getLocation()).removeFirstInLine());
			}
			else
			{
				//no one left in line
				break;
			}
		}
	}

	private static void removePassengersFromTrain(Train train)
	{
		//take people off who are at their destination
		List<Passenger> toGetOff = new ArrayList<Passenger>();
		for(Passenger passenger : train.getPassengers())
		{
			if(passenger.getDestination() == train.getLocation())
			{
				//passenger is at destination so remove them from train
				toGetOff.add(passenger);
				//System.out.println("Passenger got off train " + i + " at station " + passenger.getDestination());
			}
		}

		//remove passengers from train
		for(Passenger p : toGetOff)
		{
			train.getPassengers().remove(p);
		}
	}

	private static void createPassengerGenerationThread()
	{
		//generate people to fill stations (different thread)
		//every 200 milliseconds (5 times per second) a person will
		//be placed in the queue at a random station
		new Thread()
		{
			public void run()
			{
				while(true)
				{
					//put someone in a queue at a random station
					int station = (int) (Math.random() * ROUTE_LENGTH);
					Station s = route.getStation(station);
					s.enqueuePassenger((new Passenger(route)));
					//System.out.println("Placed passenger at station: " + station);
					try
					{
						Thread.sleep(10);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}