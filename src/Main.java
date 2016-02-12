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
				trainA.getPassengers();
				
				
				//take people off who are at their destination 
			}
		}
		
		// move trains to next station
		//not sure about its but ok
		for(Train trainGeneric = trainA ; trainGeneric!=null;){
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
			
		}
		// disenbark people who arrived at destination
		// fill train with people from que
		// generate 15 people, place randomly at stations 
		// goto here:
		
	}

}
