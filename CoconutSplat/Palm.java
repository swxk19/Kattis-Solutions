import java.util.*;

public class Palm extends Hand {

	int playerId;
	
	public int getId() {
		return this.playerId;
	}
	public Palm(int playerId) {
		this.playerId = playerId;
	}

	public void splat(Deque<Hand> deque) {

	}
}
