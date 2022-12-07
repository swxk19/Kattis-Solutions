import java.util.*;

public class Fist extends Hand {
	int playerId;

	public int getId() {
		return this.playerId;
	}
	public Fist(int playerId) {
		this.playerId = playerId;
	}

	public void splat(Deque<Hand> deque) {
		deque.addLast(new Palm(this.playerId));
	}
}
