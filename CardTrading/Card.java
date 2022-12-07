import java.util.*;

public class Card {

	int type;
	long buyPrice;
	long sellPrice;
	int count = 0;
       	long profitToCombo;
	long sellAll;	
	long buyToCombo;
	
	public Card(int type, long buyPrice, long sellPrice) {
		this.type = type;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
	}

	@Override
	public String toString() {
		return "Type: " + this.type + ", ProfitCombo: " + profitToCombo;
	}

	public void evalCostToCombo() {
		this.profitToCombo = ((2 - this.count) * buyPrice) + (count * sellPrice); 
		this.sellAll = count * sellPrice;
		this.buyToCombo = (2 - this.count) * buyPrice;
	}
}
