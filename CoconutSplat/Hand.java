import java.util.*;

abstract class Hand {
	int playerId;

	abstract void splat(Deque<Hand> deque);
	abstract int getId();
}
