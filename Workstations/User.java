import java.util.*;

public class User {
	int arrivalTime;
	int stayTime;

	public User(int arrivalTime, int stayTime) {
		this.arrivalTime = arrivalTime;
		this.stayTime = stayTime;
	}
	
	@Override
	public String toString() {
		return "Arrival Time: " + this.arrivalTime + ", Stay Time: " + this.stayTime;
	}
}
