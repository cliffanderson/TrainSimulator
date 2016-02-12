package src;

public class Main {

	public static void main(String[] args) {
		//specify size of route
		final int routeLength = 5;
		
		//create route
		final TrainRoute route = new TrainRoute(routeLength);
		
		// place 2 trains at start and end
		Train trainA = new Train(0);
		Train trainB = new Train(routeLength-1);
		
		
		// generate people to fill stations (different thread)
		//every 200 milliseconds (5 times per second) a person will be placed in a random queue
		new Thread()
		{
			public void run()
			{
				//put someone in a queue at a random station
				route.getStation((int) (Math.random() * routeLength)).getLine().add(new Passenger(route));
				try
				{
					Thread.sleep(200);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}.start();
		
		
		// here: board trains with people from queue
		for (int i=0;i<routeLength; i++){
			if (trainA.getLocation()==route.getRoute(i).getID()){
				//take people off who are at their destination 
			}
		}
		
		
		// move trains to next station
		// disembark people who arrived at destination
		// fill train with people from que
		// generate 15 people, place randomly at stations 
		// goto here:
		
	}

}
