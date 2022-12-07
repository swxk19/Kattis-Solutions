import java.util.*;

public class Coconut extends Hand {
	
	int playerId;

	public Coconut(int playerId) {
		this.playerId = playerId;
	}

	public int getId() {
		return this.playerId;
	}

	public void splat(Deque<Hand> deque) {
		deque.addFirst(new Fist(this.playerId));
		deque.addFirst(new Fist(this.playerId));
	}
}
