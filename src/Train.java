package src;

public class Train {
	
	public Train(int startLocation){
		passengers=new TeamLinkedBag<Passenger>();
		location=startLocation;
		if(location==0){
			this.direction=Direction.RIGHT;	
		}
		else {
			this.direction=Direction.LEFT;	
		}
	}
	public int getLocation(){
		return location;
	}
	public void setLocation(int newLocal){
		location=newLocal;
	}
	public TeamLinkedBag<Passenger> getPassengers(){
		return passengers;
	}
	public void boadTrain(Passenger newPassenger){
		passengers.add(newPassenger);
	}
	public Direction getDirection(){
		return direction;
	}
	public void setDirection(Direction newDir){
		direction=newDir;
	}
	
	
private int location; 
private Direction direction;
// max capacity
private TeamLinkedBag<Passenger> passengers;
}