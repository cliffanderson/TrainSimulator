package main;


public class TrainRoute
{
	public TrainRoute(int NumofStations)
	{
		// generate a list of stations 
		route = new Station[NumofStations];

		for (int i=0;i<NumofStations;i++)
		{
			route[i]= new Station(i);
		}
	}

	public Station getStation(int i){
		return route[i];
	}

	public int getNumStations()
	{
		return this.route.length;
	}

	private Station[] route;
}
