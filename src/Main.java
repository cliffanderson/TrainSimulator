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
			if (trainA.getLocation()==route.getStation(i).getID()){
				trainA.getPassengers();
				
				
				//take people off who are at their destination 
			}
		}
		
		// move trains to next station
		// disembark people who arrived at destination
		
		
		//primary loop
		while (true){
			
			for(Train trainGeneric = trainA ; trainGeneric!=null; trainGeneric= trainB){
				// if headed right
				if (trainGeneric.getDirection().equals(Direction.RIGHT)){
					
					if (trainGeneric.getLocation()==(routeLength-1)){
						trainGeneric.setDirection(Direction.LEFT);
						trainGeneric.setLocation(trainGeneric.getLocation()-1);
					}
					else{
						trainGeneric.setLocation(trainGeneric.getLocation()+1);
					}
				}
				//if headed left
				if (trainGeneric.getDirection().equals(Direction.LEFT)){
					//if at end of track
					if (trainGeneric.getLocation()==(0)){
						trainGeneric.setDirection(Direction.RIGHT);
						trainGeneric.setLocation(trainGeneric.getLocation()+1);
					}
					else{
						trainGeneric.setLocation(trainGeneric.getLocation()-1);
					}
				}
				if (trainGeneric.equals(trainB)){
					trainGeneric=null;
				}	
			}
		}
		// disenbark people who arrived at destination
		// fill train with people from que
		// generate 15 people, place randomly at stations 
		// goto here:
		
	}

}
