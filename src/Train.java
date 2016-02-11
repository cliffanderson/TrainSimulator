package src;


public class Train {
	public Train(int startLocation){
		passengers=new TeamLinkedBag<Passenger>();
		location=startLocation;
	}
	public int getLocation(){
		return location;
	}
	public TeamLinkedBag<Passenger> getPassengers(){
		return passengers;
	}
	public void boadTrain(Passenger newPassenger){
		passengers.add(newPassenger);
	}
	
	
int location; 
// max capacity
private TeamLinkedBag<Passenger> passengers;
}