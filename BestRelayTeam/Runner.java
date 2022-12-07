public class Runner {
	String name;
	double firstLap;
	double otherLap;
	
	public Runner(String name, double firstLap, double otherLap) {
		this.name = name;
		this.firstLap = firstLap;
		this.otherLap = otherLap;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
