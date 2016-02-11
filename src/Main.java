package src;

public class Main {

	public static void main(String[] args) {
		//specificy size of route
		int routeLength=5;
		//create route
		TrainRoute route= new TrainRoute(routeLength);
		// place 2 trains at start and end
		Train trainA = new Train(0);
		Train trainB = new Train(routeLength-1);
		
		
		// generate people to fill stations (different thread)
		
		
		// here: board trains with people from que
		for (int i=0;i<routeLength; i++){
			if (trainA.getLocation()==route.getRoute(i).getID()){
				//take people off who are at their destination 
			}
		}
		
		
		// move trains to next station
		// disenbark people who arrived at destination
		// fill train with people from que
		// generate 15 people, place randomly at stations 
		// goto here:
		
	}

}
