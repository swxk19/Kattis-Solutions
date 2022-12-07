import java.util.*;
import java.io.*;

public class Quest {
	
	static int numQuest = 0;
	int id;
	long gold;
	int energy;

	public Quest (int energy, long gold) {
		this.id = numQuest;
		this.gold = gold;
		this.energy = energy;
		numQuest++;
	}
}
