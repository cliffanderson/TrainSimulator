package src;
import java.util.LinkedList;

public class TrainRoute {
	public TrainRoute(int NumofStations){
		// generate a list of stations 
		list =new LinkedList<Station>();
		
		for (int i=0;i<NumofStations;i++){
			Station station = new Station(i);
			list.push(station);
		}
	}
	public LinkedList<Station> getRoute(){
		return list;
	}
private LinkedList<Station> list;
}
