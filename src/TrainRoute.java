package src;
import java.util.Arrays;

public class TrainRoute {
	public TrainRoute(int NumofStations){
		// generate a list of stations 
		
		route =new Station[NumofStations];
		
		for (int i=0;i<NumofStations;i++){
			route[i]= new Station(i);
		}
	}
	public Station getRoute(int i){
		return route[i];
	}
	
private Station[] route;
}
