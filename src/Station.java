package src;
import java.util.PriorityQueue;

public class Station {

	public Station(int count){
		WaitLine= new PriorityQueue<Passenger>();
		ID=count;
	}
	public void BoardPassenger(){
		//pop top member of que of que and load to train
	}
	public void DesembarkPassenger(){
		//remove passengers from train who's destination==station id
	}
	

private PriorityQueue<Passenger> WaitLine;
private int ID;
}