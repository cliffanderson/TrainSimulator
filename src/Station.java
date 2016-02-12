package src;
import java.util.PriorityQueue;

public class Station {

	public Station(int count){
		waitLine= new PriorityQueue<Passenger>();
		id=count;
	}
	public void boardPassenger(){
		//pop top member of que and load to train
	}
	
	public PriorityQueue<Passenger> getLine(){
		return waitLine;
	}
	public int getID(){
		return id;
	}
	
boolean trainPresent;
private PriorityQueue<Passenger> waitLine;
private int id;
}